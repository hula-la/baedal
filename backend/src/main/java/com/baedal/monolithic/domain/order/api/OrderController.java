package com.baedal.monolithic.domain.order.api;

import com.baedal.monolithic.domain.order.application.OrderService;
import com.baedal.monolithic.domain.order.dto.OrderDto;
import com.baedal.monolithic.domain.order.entity.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<Map<OrderStatus,List<OrderDto.SummarizedInfo>>> findAll(){
        Long accountId = 1L;
        return ResponseEntity.ok()
                .body(orderService.findAllOrders(accountId));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto.DetailedInfo> find(@PathVariable Long orderId){
        Long accountId = 1L;
        return ResponseEntity.ok()
                .body(orderService.findOrder(accountId, orderId));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody OrderDto.PostReq orderPostReq){
        Long accountId = 1L;
        Long orderId = orderService.createOrder(accountId, orderPostReq);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(orderId).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> delete(@PathVariable Long orderId){
        Long accountId = 1L;
        orderService.deleteOrder(accountId, orderId);
        return ResponseEntity.noContent().build();
    }

}
