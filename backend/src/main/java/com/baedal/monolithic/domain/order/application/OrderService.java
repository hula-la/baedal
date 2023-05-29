package com.baedal.monolithic.domain.order.application;

import com.baedal.monolithic.domain.account.application.AddressService;
import com.baedal.monolithic.domain.order.dto.OrderDto;
import com.baedal.monolithic.domain.order.entity.Order;
import com.baedal.monolithic.domain.order.entity.OrderMenu;
import com.baedal.monolithic.domain.order.entity.OrderStatus;
import com.baedal.monolithic.domain.order.exception.OrderException;
import com.baedal.monolithic.domain.order.exception.OrderStatusCode;
import com.baedal.monolithic.domain.order.repository.OrderMenuRepository;
import com.baedal.monolithic.domain.order.repository.OrderRepository;
import com.baedal.monolithic.domain.store.application.MenuService;
import com.baedal.monolithic.domain.store.application.StoreService;
import com.baedal.monolithic.domain.store.dto.StoreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMenuRepository orderMenuRepository;
    private final StoreService storeService;
    private final AddressService addressService;
    private final MenuService menuService;
    private final OrderMapper orderMapper;


    @Transactional(readOnly = true)
    public Map<OrderStatus, List<OrderDto.SummarizedInfo>> findAllOrders(Long accountId) {

        Map<OrderStatus, List<OrderDto.SummarizedInfo>> map = new EnumMap<>(OrderStatus.class);

        List<Order> orders = orderRepository.findAllByAccountIdOrderByOrderAtDesc(accountId);

        for (Order order:orders){
            StoreDto.SummarizedInfo store = storeService.findStoreIntro(order.getStoreId());

            if (!map.containsKey(order.getStatus())) map.put(order.getStatus(),new ArrayList<>());

            OrderDto.SummarizedInfo orderIntro = orderMapper.mapToSummarizedOrderDto(order, store.getName());

            map.get(order.getStatus()).add(orderIntro);
        }

        return map;
    }

    @Transactional(readOnly = true)
    public OrderDto.DetailedInfo findOrder(Long accountId, Long orderId) {

        Order order = findOrderEntity(orderId);

        if (!accountId.equals(order.getAccountId())) throw new OrderException(OrderStatusCode.NO_ACCESS);

        StoreDto.SummarizedInfo store = storeService.findStoreIntro(order.getStoreId());

        return orderMapper.mapToDetailedOrderDto(order,
                order.getAddressDetail(), store.getName(), getOrderMenuDto(order));
    }

    @Transactional(readOnly = true)
    public List<String> getMenuNames(Long orderId) {

        Order order = findOrderEntity(orderId);

        return getOrderMenuDto(order)
                .stream()
                .map(OrderDto.Menu::getMenuName)
                .collect(Collectors.toList());

    }

    @Transactional
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

        Long orderPrice = menuService.calculateOrderPrice(orderPostReq.getMenus());
        Long addressId = addressService.getAddressIdByAccountId(accountId);
        Long deliveryTip = calculateTip(orderPostReq.getStoreId(),orderPrice, addressId);
        String menuSummary = menuService.summaryMenu(orderPostReq.getMenus());

        Order order = orderMapper
                .mapToOrderEntity(orderPostReq, accountId, orderPrice, addressId, deliveryTip, menuSummary);

        Order savedOrder = orderRepository.save(order);

        createOrderMenus(savedOrder, orderPostReq.getMenus());

        return savedOrder.getId();
    }

//    private Timestamp calculateExArrivalTime(Order order, int minute) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTimeInMillis(order.getOrderAt().getTime());
//        cal.add(Calendar.MINUTE, minute);
//        return new Timestamp(cal.getTime().getTime());
//    }

    @Transactional
    public void createOrderMenus(Order order, List<OrderDto.MenuPostReq> menus) {

        for (OrderDto.MenuPostReq menu:menus) {
            OrderMenu orderMenu = orderMapper.mapToOrderMenuEntity(menu, order);

            orderMenuRepository.save(orderMenu);
        }
    }

    private Long calculateTip(Long storeId, Long orderPrice, Long addressId) {

        return storeService.getTipByAddressId(storeId,addressId)
                + storeService.getTipByPrice(storeId,orderPrice);

    }

    private Order findOrderEntity(Long orderId) {

        return orderRepository.findById(orderId)
                .orElseThrow(()->new OrderException(OrderStatusCode.NO_ORDER));

    }

    private List<OrderDto.Menu> getOrderMenuDto(Order order) {

         return order.getOrderMenus().stream()
                 .map(orderMenu ->
                        orderMapper.mapToMenuDto(orderMenu,
                                menuService.getMenuName(orderMenu.getMenuId())))
                .collect(Collectors.toList());

    }

}
