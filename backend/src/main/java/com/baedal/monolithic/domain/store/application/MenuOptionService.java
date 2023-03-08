package com.baedal.monolithic.domain.store.application;

import com.baedal.monolithic.domain.store.dto.MenuDto;
import com.baedal.monolithic.domain.store.entity.StoreMenuOption;
import com.baedal.monolithic.domain.store.entity.StoreMenuOptionGroup;
import com.baedal.monolithic.domain.store.exception.StoreException;
import com.baedal.monolithic.domain.store.exception.StoreStatusCode;
import com.baedal.monolithic.domain.store.repository.StoreMenuOptionGroupRepository;
import com.baedal.monolithic.domain.store.repository.StoreMenuOptionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuOptionService {

    private final StoreMenuOptionRepository storeMenuOptionRepository;
    private final StoreMenuOptionGroupRepository storeMenuOptionGroupRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<MenuDto.OptionGroup> findAllMenuOptionGroupsByMenuId(Long menuId) {
        return storeMenuOptionGroupRepository.findAllByMenuIdOrderByPriority(menuId)
                .stream()
                .map(group -> {
                    MenuDto.OptionGroup menuOptionGroupDto = modelMapper.map(group, MenuDto.OptionGroup.class);
                    menuOptionGroupDto.setOptions(findAllMenuOptionsByGroupId(menuOptionGroupDto.getId()));
                    return menuOptionGroupDto;
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MenuDto.Option> findAllMenuOptionsByGroupId(Long groupId) {
        return storeMenuOptionRepository.findAllByGroupIdOrderByGroupId(groupId)
                .stream()
                .map(group -> modelMapper.map(group, MenuDto.Option.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public StoreMenuOption findMenuOptionEntity(Long optionId) {
        return storeMenuOptionRepository.findById(optionId)
                .orElseThrow(()->
                        new StoreException(StoreStatusCode.NO_OPTION));
    }

    @Transactional(readOnly = true)
    public StoreMenuOptionGroup findMenuOptionGroupEntity(Long groupId) {
        return storeMenuOptionGroupRepository.findById(groupId)
                .orElseThrow(()->
                        new StoreException(StoreStatusCode.NO_OPTION_GROUP));
    }

}
