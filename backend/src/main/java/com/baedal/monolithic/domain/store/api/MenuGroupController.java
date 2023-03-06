package com.baedal.monolithic.domain.store.api;

import com.baedal.monolithic.domain.store.application.MenuGroupService;
import com.baedal.monolithic.domain.store.dto.MenuDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stores/{storeId}")
@RequiredArgsConstructor
public class MenuGroupController {

    private final MenuGroupService menuGroupService;
    private final ModelMapper modelMapper;

    @GetMapping("/menu-groups")
    public ResponseEntity<MenuDto.GetRes> findAllMenuGroups (@PathVariable Long storeId) {
        return ResponseEntity.ok(new MenuDto.GetRes(menuGroupService.findAllMenuGroups(storeId)));
    }

    @GetMapping("/menu/{menuId}")
    public ResponseEntity<MenuDto.DetailedInfo> findAllOptionGroups (@PathVariable Long menuId) {
        return ResponseEntity.ok(menuGroupService.findMenuDetail(menuId));
    }

}
