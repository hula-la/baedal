package com.baedal.monolithic.domain.store.api;

import com.baedal.monolithic.domain.store.application.MenuGroupService;
import com.baedal.monolithic.domain.store.dto.MenuGroupFindAllDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/stores/{storeId}/menu-groups")
@RequiredArgsConstructor
public class MenuGroupController {

    private final MenuGroupService menuGroupService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<MenuGroupFindAllRes> findAll (@PathVariable Long storeId) {
        return ResponseEntity.ok(new MenuGroupFindAllRes(menuGroupService.findAllMenuGroups(storeId)));
    }

    @AllArgsConstructor
    private static class MenuGroupFindAllRes {
        private List<MenuGroupFindAllDto> menuGroups;
    }

}
