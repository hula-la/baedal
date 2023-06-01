package com.baedal.monolithic.domain.owner.api;

import com.baedal.monolithic.domain.store.application.MenuService;
import com.baedal.monolithic.domain.store.dto.MenuPostDto;
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


    // 순서바꾸기, 메뉴 정보 변경, 옵션 생성, 메뉴 생성, 옵션 그룹 생성, 메뉴 그룹 생성,
    @PostMapping("/menu-group")
    public ResponseEntity<Void> createMenuGroup (@PathVariable Long storeId,
                                                 @Valid @RequestBody MenuPostDto.MenuGroupReq menuGroupReq) {

        Long menuGroupId = menuService.createMenuGroup(storeId, menuGroupReq);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(menuGroupId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/menu-group/{menuGroupId}/menu")
    public ResponseEntity<Void> createMenu (@PathVariable Long menuGroupId,
                                            @Valid @RequestBody MenuPostDto.MenuReq menuReq) {

        Long menuId = menuService.createMenu(menuGroupId, menuReq);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(menuId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/menu/{menuId}/option-group")
    public ResponseEntity<Void> createOptionGroup (@PathVariable Long menuId,
                                                   @Valid @RequestBody MenuPostDto.OptionGroupReq optionGroupReq) {
        Long optionGroupId = menuService.createOptionGroup(menuId, optionGroupReq);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(optionGroupId).toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/option-group/{optionGroupId}/option")
    public ResponseEntity<Void> createOption (@PathVariable Long optionGroupId,
                                              @Valid @RequestBody MenuPostDto.OptionReq optionReq) {

        Long optionId = menuService.createOption(optionGroupId, optionReq);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(optionId).toUri();

        return ResponseEntity.created(location).build();
    }


}
