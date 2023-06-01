package com.baedal.monolithic.domain.order.application;

import com.baedal.monolithic.domain.order.dto.OrderDto;
import com.baedal.monolithic.domain.order.entity.Order;
import com.baedal.monolithic.domain.order.entity.OrderMenu;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
class OrderMapper {

    protected Order mapToOrderEntity(OrderDto.PostReq orderPostReq,
                                     Long accountId,
                                     Long orderPrice,
                                     Long addressId,
                                     Long deliveryTip,
                                     String menuSummary){

        return  Order.builder()
                .accountId(accountId)
                .storeId(orderPostReq.getStoreId())
                .addressId(addressId)
                .addressDetail(orderPostReq.getAddressDetail())
                .menuSummary(menuSummary)
                .disposableReq(orderPostReq.getDisposableReq())
                .kimchiReq(orderPostReq.getKimchiReq())
                .riderMsg(orderPostReq.getRiderMsg())
                .ownerMsg(orderPostReq.getOwnerMsg())
                .deliveryTip(deliveryTip)
                .orderPrice(orderPrice)
                .totalPrice(orderPrice + deliveryTip)
                .build();
    }

    protected OrderMenu mapToOrderMenuEntity(OrderDto.MenuPostReq menu, Order order){

        return  OrderMenu.builder()
                .order(order)
                .menuId(menu.getMenuId())
                .count(menu.getCount())
                // option 바꾸기
                .options("옵션")
                .build();
    }

    protected OrderDto.DetailedInfo mapToDetailedOrderDto(Order order,
                                                          String address,
                                                          String storeName,
                                                          List<OrderDto.Menu> menus){

        return  OrderDto.DetailedInfo.builder()
                .id(order.getId())
                .storeName(storeName)
                .address(address)
                .status(order.getStatus())
                .exArrivalTime(order.getExArrivalTime())
                .orderAt(order.getOrderAt())
                .disposableReq(order.getDisposableReq())
                .kimchiReq(order.getKimchiReq())
                .riderMsg(order.getRiderMsg())
                .ownerMsg(order.getOwnerMsg())
                .deliveryTip(order.getDeliveryTip())
                .orderPrice(order.getOrderPrice())
                .totalPrice(order.getTotalPrice())
                .menus(menus)
                .build();
    }

    protected OrderDto.SummarizedInfo mapToSummarizedOrderDto(Order order, String name){

        return  OrderDto.SummarizedInfo.builder()
                .id(order.getId())
                .name(name)
                .menuSummary(order.getMenuSummary())
                .totalPrice(order.getTotalPrice())
                .orderAt(order.getOrderAt())
                .status(order.getStatus())
                .exArrivalTime(order.getExArrivalTime())
                .orderAt(order.getOrderAt())
                .build();
    }

    public OrderDto.Menu mapToMenuDto(OrderMenu orderMenu, String menuName){

        return  OrderDto.Menu.builder()
                .id(orderMenu.getId())
                .menuName(menuName)
                .cnt(orderMenu.getCount())
                .options(orderMenu.getOptions())
                .build();
    }

}
