package com.baedal.monolithic.domain.order.repository;

import com.baedal.monolithic.domain.order.entity.Order;
import com.baedal.monolithic.domain.store.entity.StoreMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByAccountIdOrderByOrderAtDesc(Long accountId);

}
