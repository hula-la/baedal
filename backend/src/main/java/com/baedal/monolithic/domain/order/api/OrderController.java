package com.baedal.monolithic.domain.order.api;

import com.baedal.monolithic.domain.order.application.OrderService;
import com.baedal.monolithic.domain.order.dto.OrderFindDetailDto;
import com.baedal.monolithic.domain.order.dto.OrderFindIntroDto;
import com.baedal.monolithic.domain.order.dto.OrderMenuDto;
import com.baedal.monolithic.domain.order.dto.OrderStatus;
import com.baedal.monolithic.domain.store.dto.StoreFindAllDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<Map<OrderStatus,List<OrderFindIntroDto>>> findAll(){
        Long accountId = 1L;
        return ResponseEntity.ok()
                .body(orderService.findAllOrders(accountId));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderFindDetailDto> find(@PathVariable Long orderId){
        Long accountId = 1L;
        return ResponseEntity.ok()
                .body(orderService.findOrder(accountId, orderId));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody OrderPostRes orderPostRes){
        Long accountId = 1L;
        Long orderId = orderService.createOrder(accountId, orderPostRes);

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

    @AllArgsConstructor
    @Getter
    public static class OrderPostRes {

        @NotNull(message = "{notnull}")
        private Long storeId;

        @NotNull(message = "{notnull}")
        private Boolean disposableReq;

        @NotNull(message = "{notnull}")
        private Boolean kimchiReq;

        private String riderMsg;
        private String ownerMsg;
        private List<OrderMenuPostRes> menus;
        private String addressDetail;

    }

    @AllArgsConstructor
    @Getter
    public static class OrderMenuPostRes {

        private Long menuId;
        private Integer count;
        private Map<Long,List<Long>> options;

    }

}
