package com.baedal.monolithic.domain.store.application;

import com.baedal.monolithic.domain.store.dto.MenuDetailDto;
import com.baedal.monolithic.domain.store.dto.MenuDto;
import com.baedal.monolithic.domain.store.dto.MenuGroupFindAllDto;
import com.baedal.monolithic.domain.store.entity.StoreMenu;
import com.baedal.monolithic.domain.store.exception.StoreException;
import com.baedal.monolithic.domain.store.exception.StoreStatusCode;
import com.baedal.monolithic.domain.store.repository.StoreMenuGroupRepository;
import com.baedal.monolithic.domain.store.repository.StoreMenuRepository;
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
    public List<MenuGroupFindAllDto> findAllMenuGroups(Long storeId) {
        return storeMenuGroupRepository.findByStoreIdOrderByPriority(storeId)
                .stream()
                .map(group -> {
                    MenuGroupFindAllDto menuGroupDto = modelMapper.map(group, MenuGroupFindAllDto.class);
                    menuGroupDto.setMenus(findAllMenuByGroupId(menuGroupDto.getId()));
                    return menuGroupDto;
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MenuDto> findAllMenuByGroupId(Long groupId) {
        return storeMenuRepository.findByGroupIdOrderByPriority(groupId)
                .stream()
                .map(group -> modelMapper.map(group, MenuDto.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MenuDetailDto findMenuDetail(Long menuId) {
        StoreMenu storeMenu = storeMenuRepository.findById(menuId)
                .orElseThrow(() -> new StoreException(StoreStatusCode.NO_MENU));
        MenuDetailDto menuDetailDto = modelMapper.map(storeMenu, MenuDetailDto.class);
        menuDetailDto.setOptionGroup(menuOptionService.findAllMenuOptionGroupsByMenuId(menuId));
        return menuDetailDto;
    }

    @Transactional(readOnly = true)
    public StoreMenu findMenuEntity(Long menuId) {
        return storeMenuRepository.findById(menuId)
                .orElseThrow(() -> new StoreException(StoreStatusCode.NO_MENU));
    }

}
