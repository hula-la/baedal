package com.baedal.monolithic.domain.owner.api;

import com.baedal.monolithic.domain.store.application.MenuService;
import com.baedal.monolithic.domain.store.dto.MenuPutPostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owner/stores/{storeId}")
public class OwnerStoreMenuController {

    private final MenuService menuService;


    // 옵션 생성, 메뉴 생성, 옵션 그룹 생성, 메뉴 그룹 생성,
    @PostMapping("/menu-group")
    public ResponseEntity<Void> createMenuGroup (@PathVariable Long storeId,
                                                 @Valid @RequestBody MenuPutPostDto.MenuGroupReq menuGroupReq) {

        Long menuGroupId = menuService.createMenuGroup(storeId, menuGroupReq);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(menuGroupId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/menu-group/{menuGroupId}/menu")
    public ResponseEntity<Void> createMenu (@PathVariable Long menuGroupId,
                                            @Valid @RequestBody MenuPutPostDto.MenuReq menuReq) {

        Long menuId = menuService.createMenu(menuGroupId, menuReq);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(menuId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/menu-group/{menuGroupId}/menu/{menuId}/option-group")
    public ResponseEntity<Void> createOptionGroup (@PathVariable Long menuId,
                                                   @Valid @RequestBody MenuPutPostDto.OptionGroupReq optionGroupReq) {
        Long optionGroupId = menuService.createOptionGroup(menuId, optionGroupReq);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(optionGroupId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/menu-group/{menuGroupId}/menu/{menuId}/option-group/{optionGroupId}/option")
    public ResponseEntity<Void> createOption (@PathVariable Long optionGroupId,
                                              @Valid @RequestBody MenuPutPostDto.OptionReq optionReq) {

        Long optionId = menuService.createOption(optionGroupId, optionReq);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(optionId).toUri();

        return ResponseEntity.created(location).build();
    }

//    순서바꾸기, 메뉴 정보 변경
    @PutMapping("/menu-group/{menuGroupId}")
    public ResponseEntity<Void> updateMenuGroup (@PathVariable Long menuGroupId,
                                                 @Valid @RequestBody MenuPutPostDto.MenuGroupReq menuGroupReq) {

        menuService.updateMenuGroup(menuGroupId, menuGroupReq);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/menu-group/{menuGroupId}/menu/{menuId}")
    public ResponseEntity<Void> updateMenu (@PathVariable Long menuId,
                                            @Valid @RequestBody MenuPutPostDto.MenuReq menuReq) {

        menuService.updateMenu(menuId, menuReq);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/menu-group/{menuGroupId}/menu/{menuId}/option-group/{optionGroupId}")
    public ResponseEntity<Void> updateptionGroup (@PathVariable Long optionGroupId,
                                                   @Valid @RequestBody MenuPutPostDto.OptionGroupReq optionGroupReq) {
        menuService.updateOptionGroup(optionGroupId, optionGroupReq);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/menu-group/{menuGroupId}/menu/{menuId}/option-group/{optionGroupId}/option/{optionId}")
    public ResponseEntity<Void> updateOption (@PathVariable Long optionId,
                                              @Valid @RequestBody MenuPutPostDto.OptionReq optionReq) {

        menuService.updateOption(optionId, optionReq);

        return ResponseEntity.noContent().build();
    }

//    삭제
    @DeleteMapping("/menu-group/{menuGroupId}")
    public ResponseEntity<Void> deleteMenuGroup (@PathVariable Long menuGroupId) {

        menuService.deleteMenuGroup(menuGroupId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/menu-group/{menuGroupId}/menu/{menuId}")
    public ResponseEntity<Void> deleteMenu (@PathVariable Long menuId) {

        menuService.deleteMenu(menuId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/menu-group/{menuGroupId}/menu/{menuId}/option-group/{optionGroupId}")
    public ResponseEntity<Void> deleteptionGroup (@PathVariable Long optionGroupId) {

        menuService.deleteOptionGroup(optionGroupId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/menu-group/{menuGroupId}/menu/{menuId}/option-group/{optionGroupId}/option/{optionId}")
    public ResponseEntity<Void> deleteOption (@PathVariable Long optionId) {

        menuService.deleteOption(optionId);

        return ResponseEntity.noContent().build();
    }
    
}
