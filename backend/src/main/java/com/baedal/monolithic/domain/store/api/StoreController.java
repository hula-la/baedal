package com.baedal.monolithic.domain.store.api;

import com.baedal.monolithic.domain.store.application.StoreService;
import com.baedal.monolithic.domain.store.dto.PageVO;
import com.baedal.monolithic.domain.store.dto.StoreFindAllDto;
import com.baedal.monolithic.domain.store.dto.StoreFindDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<StoreFindAllRes> findAll (@Valid @ModelAttribute StoreReq storeReq) {
        return ResponseEntity.ok(
                new StoreFindAllRes(
                        storeService.countStores(storeReq),
                        storeService.findAllStores(storeReq)
                )
        );
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<StoreFindDto> find (@RequestParam Long storeId) {
        return ResponseEntity.ok(storeService.findStore(storeId));
    }


    @Getter
    public static class StoreReq {

        @NotNull(message = "{notnull}")
        private Long categoryId;

        @NotNull(message = "{notnull}")
        private Long addressId;

        private PageVO pageVO;


    }

    @AllArgsConstructor
    private static class StoreFindAllRes {

        private Long results; // 총 갯수
        private List<StoreFindAllDto> stores; // 가게 목록

    }


}
