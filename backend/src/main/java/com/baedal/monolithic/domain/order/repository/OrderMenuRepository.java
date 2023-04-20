package com.baedal.monolithic.domain.order.repository;

import com.baedal.monolithic.domain.order.entity.OrderMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMenuRepository extends JpaRepository<OrderMenu,Long> {

}
