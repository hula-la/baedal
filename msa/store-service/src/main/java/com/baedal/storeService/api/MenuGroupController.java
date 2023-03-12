package com.baedal.storeService.api;

import com.baedal.storeService.application.MenuGroupService;
import com.baedal.storeService.dto.MenuDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
