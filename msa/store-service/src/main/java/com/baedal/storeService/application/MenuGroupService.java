package com.baedal.storeService.application;

import com.baedal.storeService.dto.MenuDto;
import com.baedal.storeService.entity.StoreMenu;
import com.baedal.storeService.exception.StoreException;
import com.baedal.storeService.exception.StoreStatusCode;
import com.baedal.storeService.repository.StoreMenuGroupRepository;
import com.baedal.storeService.repository.StoreMenuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuGroupService {

    private final StoreMenuGroupRepository storeMenuGroupRepository;
    private final StoreMenuRepository storeMenuRepository;
    private final MenuOptionService menuOptionService;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<MenuDto.Group> findAllMenuGroups(Long storeId) {
        return storeMenuGroupRepository.findByStoreIdOrderByPriority(storeId)
                .stream()
                .map(group -> {
                    MenuDto.Group menuGroupDto = modelMapper.map(group, MenuDto.Group.class);
                    menuGroupDto.setMenus(findAllMenuByGroupId(menuGroupDto.getId()));
                    return menuGroupDto;
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MenuDto.SummarizedInfo> findAllMenuByGroupId(Long groupId) {
        return storeMenuRepository.findByGroupIdOrderByPriority(groupId)
                .stream()
                .map(group -> modelMapper.map(group, MenuDto.SummarizedInfo.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MenuDto.DetailedInfo findMenuDetail(Long menuId) {
        StoreMenu storeMenu = storeMenuRepository.findById(menuId)
                .orElseThrow(() -> new StoreException(StoreStatusCode.NO_MENU));
        MenuDto.DetailedInfo menuDetailDto = modelMapper.map(storeMenu, MenuDto.DetailedInfo.class);
        menuDetailDto.setOptionGroup(menuOptionService.findAllMenuOptionGroupsByMenuId(menuId));
        return menuDetailDto;
    }

    @Transactional(readOnly = true)
    public StoreMenu findMenuEntity(Long menuId) {
        return storeMenuRepository.findById(menuId)
                .orElseThrow(() -> new StoreException(StoreStatusCode.NO_MENU));
    }

}
