package com.baedal.monolithic.domain.store.application;

import com.baedal.monolithic.domain.store.dto.MenuDto;
import com.baedal.monolithic.domain.store.dto.MenuGroupFindAllDto;
import com.baedal.monolithic.domain.store.dto.MenuOptionDto;
import com.baedal.monolithic.domain.store.repository.StoreMenuGroupRepository;
import com.baedal.monolithic.domain.store.repository.StoreMenuRepository;
import com.baedal.monolithic.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuGroupService {

    private final StoreMenuGroupRepository storeMenuGroupRepository;
    private final StoreMenuRepository storeMenuRepository;
    private final MenuOptionService menuOptionService;
    private final ModelMapper modelMapper;

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

    public List<MenuDto> findAllMenuByGroupId(Long groupId) {
        return storeMenuRepository.findByGroupIdOrderByPriority(groupId)
                .stream()
                .map(group -> modelMapper.map(group, MenuDto.class))
                .collect(Collectors.toList());
    }

}
