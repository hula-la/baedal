package com.baedal.monolithic.domain.order.repository;

import com.baedal.monolithic.domain.order.entity.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByAccountIdOrderByOrderAtDesc(Long accountId);

    @EntityGraph(attributePaths = {"orderMenus"})
    Optional<Order> findDetailedOrderById(Long orderId);

}
