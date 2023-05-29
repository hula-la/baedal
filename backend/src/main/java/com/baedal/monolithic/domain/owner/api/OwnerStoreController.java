package com.baedal.monolithic.domain.owner.api;

import com.baedal.monolithic.domain.owner.application.OwnerStoreService;
import com.baedal.monolithic.domain.store.dto.StoreDto;
import com.baedal.monolithic.global.util.AccountId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owner/stores")
public class OwnerStoreController {

    private final OwnerStoreService ownerStoreService;


    @PostMapping
    public ResponseEntity<Void> create(@AccountId Long accountId,
                                       @Valid @RequestBody StoreDto.PostPutReq storePostPutReq) {

        Long storeId = ownerStoreService.createStore(accountId, storePostPutReq);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(storeId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{storeId}")
    public ResponseEntity<Void> update(@AccountId Long accountId,
                                       @PathVariable Long storeId,
                                       @Valid @RequestBody StoreDto.PostPutReq storePostPutReq) {

        ownerStoreService.updateStore(accountId, storeId, storePostPutReq);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{storeId}")
    public ResponseEntity<Void> delete(@PathVariable Long storeId) {

        ownerStoreService.deleteStore(storeId);
        return ResponseEntity.noContent().build();
    }

}
