package com.baedal.monolithic.domain.order.application;

import com.baedal.monolithic.domain.account.application.AccountService;
import com.baedal.monolithic.domain.account.application.AddressService;
import com.baedal.monolithic.domain.order.dto.OrderDto;
import com.baedal.monolithic.domain.order.entity.OrderStatus;
import com.baedal.monolithic.domain.order.entity.Order;
import com.baedal.monolithic.domain.order.entity.OrderMenu;
import com.baedal.monolithic.domain.order.exception.OrderException;
import com.baedal.monolithic.domain.order.exception.OrderStatusCode;
import com.baedal.monolithic.domain.order.repository.OrderMenuRepository;
import com.baedal.monolithic.domain.order.repository.OrderRepository;
import com.baedal.monolithic.domain.store.application.MenuGroupService;
import com.baedal.monolithic.domain.store.application.MenuOptionService;
import com.baedal.monolithic.domain.store.application.StoreService;
import com.baedal.monolithic.domain.store.dto.StoreDto;
import com.baedal.monolithic.domain.store.entity.StoreMenuOption;
import com.baedal.monolithic.domain.store.entity.StoreMenuOptionGroup;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMenuRepository orderMenuRepository;
    private final StoreService storeService;
    private final MenuGroupService menuGroupService;
    private final AccountService accountService;
    private final AddressService addressService;
    private final MenuOptionService menuOptionService;
    private final ModelMapper modelMapper;


    public Map<OrderStatus, List<OrderDto.SummarizedInfo>> findAllOrders(Long accountId) {
        Map<OrderStatus, List<OrderDto.SummarizedInfo>> map = new EnumMap<>(OrderStatus.class);

        List<Order> orders = orderRepository.findAllByAccountIdOrderByOrderAtDesc(accountId);

        for (Order order:orders){
            StoreDto.SummarizedInfo store = storeService.findStoreIntro(order.getStoreId());

            if (!map.containsKey(order.getStatus())) map.put(order.getStatus(),new ArrayList<>());

            OrderDto.SummarizedInfo orderIntro = modelMapper.map(order, OrderDto.SummarizedInfo.class);
            orderIntro.setName(store.getName());

            map.get(order.getStatus()).add(orderIntro);
        }

        return map;
    }

    public OrderDto.DetailedInfo findOrder(Long accountId, Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()->new OrderException(OrderStatusCode.NO_ORDER));
        if (!Objects.equals(accountId, order.getAccountId())) throw new OrderException(OrderStatusCode.NO_ACCESS);

        StoreDto.SummarizedInfo store = storeService.findStoreIntro(order.getStoreId());
        OrderDto.DetailedInfo orderDto = modelMapper.map(order, OrderDto.DetailedInfo.class);

        // 추후에 address Id로 추가하기
        orderDto.setStoreName(store.getName());
        orderDto.setAddress(order.getAddressDetail());
        orderDto.setMenus(findMenusByOrderId(orderId));

        return orderDto;

    }

    public List<OrderDto.Menu> findMenusByOrderId(Long orderId) {
        return orderMenuRepository.findAllByOrderId(orderId).stream()
                .map(menu -> {
                    OrderDto.Menu menuDto = modelMapper.map(menu, OrderDto.Menu.class);
                    menuDto.setMenuName(menuGroupService.findMenuEntity(orderId).getName());
                    return menuDto;
                })
                .collect(Collectors.toList());

    }

    public void deleteOrder(Long accountId, Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()->new OrderException(OrderStatusCode.NO_ORDER));

        // 사용자가 일치하지 않을 경우 접근 권한 X
        if (!Objects.equals(accountId, order.getAccountId())) throw new OrderException(OrderStatusCode.NO_ACCESS);

        // 접수된 주문이면 삭제 불가
        if (!order.getStatus().equals(OrderStatus.WAIT)) throw new OrderException(OrderStatusCode.ALREADY_RECEIVED);

        orderRepository.delete(order);
    }

    @Transactional
    public Long createOrder(Long accountId, OrderDto.PostReq orderPostReq) {

        Order order = modelMapper.map(orderPostReq, Order.class);
        Long orderPrice = calculatePrice(orderPostReq.getMenus());
        Long userAddressId = accountService.getUserEntity(accountId).getUserAddressId();
        Long addressId = addressService.getAddressId(userAddressId);
        Long deliveryTip = calculateTip(order.getStoreId(),orderPrice, addressId);
        String menuSummary = summaryMenu(orderPostReq.getMenus());

        order.setAccountId(accountId);
        order.setAddressId(addressId);
        order.setMenuSummary(menuSummary);
        order.setStatus(OrderStatus.WAIT);
        order.setDeliveryTip(deliveryTip);
        order.setOrderPrice(orderPrice);
        order.setTotalPrice(deliveryTip+orderPrice);
        order.setOrderAt(new Timestamp(System.currentTimeMillis()));
        order.setExArrivalTime(calculateExArrivalTime(order, 40));

        Long orderId = orderRepository.save(order).getId();

        createOrderMenus(orderId, orderPostReq.getMenus());

        return orderId;
    }

    private Timestamp calculateExArrivalTime(Order order, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(order.getOrderAt().getTime());
        cal.add(Calendar.MINUTE, minute);
        return new Timestamp(cal.getTime().getTime());
    }

    private String summaryMenu(List<OrderDto.MenuPostReq> menus) {
        String menuName = menuGroupService.findMenuEntity(menus.get(0).getMenuId()).getName();
        String extra = menus.size()==1?" 1개": " 외 " + (menus.size()-1) +"개";

        return menuName + extra;
    }

    public Long calculateTip(Long storeId, Long orderPrice, Long addressId) {

        return storeService.getTipByAddressId(storeId,addressId) 
                + storeService.getTipByPrice(storeId,orderPrice);

    }

    public Long calculatePrice(List<OrderDto.MenuPostReq> menus) {
        Long price = 0L;

        for(OrderDto.MenuPostReq menu: menus){

            price += menuGroupService.findMenuEntity(menu.getMenuId()).getPrice()
                    * menu.getCount();

            for (Long optionGroupId:menu.getOptions().keySet()){
                List<Long> options = menu.getOptions().get(optionGroupId);
                for (Long optionId:options){
                    // 메뉴 안에 있는건지 확인
                    StoreMenuOption menuOption = menuOptionService.findMenuOptionEntity(optionId);

                    if (!menuOption.getGroupId().equals(optionGroupId))
                        throw new OrderException(OrderStatusCode.NOT_MATCH_OPTION_GROUP);

                    price += menuOption.getPrice();
                }

                // 조건 맞췄는지 확인
                if (!satisfyOptionCondition(options.size(),optionGroupId))
                    throw new OrderException(OrderStatusCode.NOT_MATCH_OPTION_CONDITION);
            }
        }

        return price;
    }

    @Transactional
    public void createOrderMenus(Long orderId, List<OrderDto.MenuPostReq> menus) {

        for (OrderDto.MenuPostReq menu:menus) {
            OrderMenu orderMenu = modelMapper.map(menu, OrderMenu.class);
            orderMenu.setOrderId(orderId);

            orderMenuRepository.save(orderMenu);
        }
    }

    public boolean satisfyOptionCondition(int optionNum, Long optionGroupId){
        StoreMenuOptionGroup menuOptionGroup = menuOptionService.findMenuOptionGroupEntity(optionGroupId);
        if (menuOptionGroup.isRadio()) return true;
        return optionNum >= menuOptionGroup.getMin() && optionNum <= menuOptionGroup.getMax();
    }

}
