package com.baedal.monolithic.domain.store.api;

import com.baedal.monolithic.domain.store.application.MenuGroupService;
import com.baedal.monolithic.domain.store.dto.MenuDetailDto;
import com.baedal.monolithic.domain.store.dto.MenuGroupFindAllDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/stores/{storeId}")
@RequiredArgsConstructor
public class MenuGroupController {

    private final MenuGroupService menuGroupService;
    private final ModelMapper modelMapper;

    @GetMapping("/menu-groups")
    public ResponseEntity<MenuGroupFindAllRes> findAllMenuGroups (@PathVariable Long storeId) {
        return ResponseEntity.ok(new MenuGroupFindAllRes(menuGroupService.findAllMenuGroups(storeId)));
    }

    @GetMapping("/menu/{menuId}")
    public ResponseEntity<MenuDetailDto> findAllOptionGroups (@PathVariable Long menuId) {
        return ResponseEntity.ok(menuGroupService.findMenuDetail(menuId));
    }

    @AllArgsConstructor
    @Getter
    private static class MenuGroupFindAllRes {
        private List<MenuGroupFindAllDto> menuGroups;
    }

}
