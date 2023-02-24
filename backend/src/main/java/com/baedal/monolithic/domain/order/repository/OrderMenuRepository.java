package com.baedal.monolithic.domain.order.repository;

import com.baedal.monolithic.domain.order.entity.Order;
import com.baedal.monolithic.domain.order.entity.OrderMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderMenuRepository extends JpaRepository<OrderMenu,Long> {

    List<OrderMenu> findAllByOrderId(Long orderId);

}
