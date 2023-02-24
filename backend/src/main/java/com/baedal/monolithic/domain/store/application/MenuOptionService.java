package com.baedal.monolithic.domain.store.application;

import com.baedal.monolithic.domain.store.dto.MenuDto;
import com.baedal.monolithic.domain.store.dto.MenuGroupFindAllDto;
import com.baedal.monolithic.domain.store.dto.MenuOptionDto;
import com.baedal.monolithic.domain.store.dto.MenuOptionGroupDto;
import com.baedal.monolithic.domain.store.entity.StoreMenuOption;
import com.baedal.monolithic.domain.store.entity.StoreMenuOptionGroup;
import com.baedal.monolithic.domain.store.exception.StoreException;
import com.baedal.monolithic.domain.store.exception.StoreStatusCode;
import com.baedal.monolithic.domain.store.repository.StoreMenuGroupRepository;
import com.baedal.monolithic.domain.store.repository.StoreMenuOptionGroupRepository;
import com.baedal.monolithic.domain.store.repository.StoreMenuOptionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuOptionService {

    private final StoreMenuOptionRepository storeMenuOptionRepository;
    private final StoreMenuOptionGroupRepository storeMenuOptionGroupRepository;
    private final ModelMapper modelMapper;

    public List<MenuOptionGroupDto> findAllMenuOptionGroupsByMenuId(Long menuId) {
        return storeMenuOptionGroupRepository.findAllByMenuIdOrderByPriority(menuId)
                .stream()
                .map(group -> {
                    MenuOptionGroupDto menuOptionGroupDto = modelMapper.map(group, MenuOptionGroupDto.class);
                    menuOptionGroupDto.setOptions(findAllMenuOptionsByGroupId(menuOptionGroupDto.getId()));
                    return menuOptionGroupDto;
                })
                .collect(Collectors.toList());
    }

    public List<MenuOptionDto> findAllMenuOptionsByGroupId(Long groupId) {
        return storeMenuOptionRepository.findAllByGroupIdOrderByGroupId(groupId)
                .stream()
                .map(group -> modelMapper.map(group, MenuOptionDto.class))
                .collect(Collectors.toList());
    }

    public StoreMenuOption findMenuOptionEntity(Long optionId) {
        return storeMenuOptionRepository.findById(optionId)
                .orElseThrow(()->
                        new StoreException(StoreStatusCode.NO_OPTION));
    }

    public StoreMenuOptionGroup findMenuOptionGroupEntity(Long groupId) {
        return storeMenuOptionGroupRepository.findById(groupId)
                .orElseThrow(()->
                        new StoreException(StoreStatusCode.NO_OPTION_GROUP));
    }

}
