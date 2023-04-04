package com.baedal.monolithic.domain.store.application;

import com.baedal.monolithic.domain.store.dto.MenuDto;
import com.baedal.monolithic.domain.store.entity.StoreMenu;
import com.baedal.monolithic.domain.store.exception.StoreException;
import com.baedal.monolithic.domain.store.exception.StoreStatusCode;
import com.baedal.monolithic.domain.store.repository.StoreMenuGroupRepository;
import com.baedal.monolithic.domain.store.repository.StoreMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
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
    private final StoreMapper storeMapper;

    @Transactional(readOnly = true)
    @Cacheable(value = "menus", key = "#storeId")
    public List<MenuDto.Group> findAllMenuGroups(Long storeId) {
        return storeMenuGroupRepository.findByStoreIdOrderByPriority(storeId)
                .stream()
                .map(group ->
                        storeMapper.mapToGroupDto(group, findAllMenuByGroupId(group.getId()))
                )
                .collect(Collectors.toList());
    }

    public List<MenuDto.SummarizedMenu> findAllMenuByGroupId(Long groupId) {
        return storeMenuRepository.findByGroupIdOrderByPriority(groupId)
                .stream()
                .map(storeMapper::mapToSummarizedMenuDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MenuDto.DetailedMenu findMenuDetail(Long menuId) {
        return storeMapper.mapToDetailedMenuDto(
                storeMenuRepository.findById(menuId)
                        .orElseThrow(() -> new StoreException(StoreStatusCode.NO_MENU)),
                menuOptionService.findAllMenuOptionGroupsByMenuId(menuId));
    }

    public StoreMenu findMenuEntity(Long menuId) {
        return storeMenuRepository.findById(menuId)
                .orElseThrow(() -> new StoreException(StoreStatusCode.NO_MENU));
    }

//    public Map<Long, MenuJoinDto.MenuMap> findMenusByMenuIds(List<Long> menuIds) {
//        return storeMenuRepository.findMenusByIdIn(menuIds)
//
//
//    }

//    public List<MenuDto.SummarizedMenu> findAllMenuByGroupId(Long groupId) {
//        return storeMenuRepository.findByGroupIdOrderByPriority(groupId)
//                .stream()
//                .map(storeMapper::mapToSummarizedMenuDto)
//                .collect(Collectors.toList());
//    }
//    public Map<Long, MenuJoinDto.MenuMap> findMenusByMenuIds(List<Long> menuIds) {
//        return storeMenuRepository.findMenusByIdIn(menuIds)
//
//
//    }
//    public Map<Long, MenuJoinDto.MenuMap> findMenusByMenuIds(List<Long> menuIds) {
//        return storeMenuRepository.findMenusByIdIn(menuIds)
//                .stream()
//                .collect(groupingBy(MenuJoinDto::getMenuId))
//                        .entrySet().stream()
//                        .collect(Collectors.toMap(
//                                Map.Entry::getKey,
//                                e -> e.getValue().
//                        ))
//
//
//
//                            mapping(MenuJoinDto::toMap, groupingBy(MenuJoinDto::getOptionGroupId))));
//                            groupingBy(MenuJoinDto::getOptionGroupId,
//                                groupingBy())));
//        return storeMenuRepository.findMenusByIdIn(menuIds)
//                .stream()
//                .collect(Collectors.toMap(StoreMenu::getId,
//                        storeMapper::mapToSummarizedMenuDto));
//    }
}
