package com.baedal.monolithic.domain.store.application;

import com.baedal.monolithic.domain.order.dto.OrderDto;
import com.baedal.monolithic.domain.store.dto.MenuPostDto;
import com.baedal.monolithic.domain.store.entity.*;
import com.baedal.monolithic.domain.store.exception.StoreException;
import com.baedal.monolithic.domain.store.exception.StoreStatusCode;
import com.baedal.monolithic.domain.store.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final StoreRepository storeRepository;
    private final StoreMenuRepository storeMenuRepository;
    private final StoreMenuGroupRepository storeMenuGroupRepository;
    private final StoreOptionRepository storeOptionRepository;
    private final StoreOptionGroupRepository storeOptionGroupRepository;
    private final StoreMapper storeMapper;


    @Transactional(readOnly = true)
    public String getMenuName(Long menuId) {
        return findMenuEntity(menuId).getName();
    }

    @Transactional(readOnly = true)
    public String summaryMenu(List<OrderDto.MenuPostReq> menus) {

        String menuName = findMenuEntity(menus.get(0).getMenuId()).getName();
        String extra = menus.size()==1?" 1개": " 외 " + (menus.size()-1) +"개";

        return menuName + extra;
    }

    @Transactional(readOnly = true)
    public long calculatePriceOfMenu(OrderDto.MenuPostReq menuPostReq) {

        int cnt = menuPostReq.getCount();
        long priceOfMenu = findMenuEntity(menuPostReq.getMenuId())
                .calculatePriceAndCheckValidation(menuPostReq.getOptions());

        return cnt * priceOfMenu;
    }

    private StoreMenu findMenuEntity(Long menuId) {

        return storeMenuRepository.findById(menuId)
                .orElseThrow(() -> new StoreException(StoreStatusCode.NO_MENU));
    }

    @Transactional
    public Long createMenuGroup(Long storeId, MenuPostDto.MenuGroupReq menuGroupReq) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreStatusCode.NO_STORE));

        StoreMenuGroup menuGroup = storeMapper.mapPostDtoToMenuGroupEntity(menuGroupReq, store);

        return storeMenuGroupRepository.save(menuGroup).getId();
    }

    @Transactional
    public Long createMenu(Long menuGroupId, MenuPostDto.MenuReq menuReq) {

        StoreMenuGroup storeMenuGroup = storeMenuGroupRepository.findById(menuGroupId)
                .orElseThrow(() -> new StoreException(StoreStatusCode.NO_MENU_GROUP));

        StoreMenu menuGroup = storeMapper.mapPostDtoToMenuEntity(menuReq, storeMenuGroup);

        return storeMenuRepository.save(menuGroup).getId();
    }

    @Transactional
    public Long createOptionGroup(Long menuId, MenuPostDto.OptionGroupReq optionGroupReq) {

        StoreMenu storeMenu = storeMenuRepository.findById(menuId)
                .orElseThrow(() -> new StoreException(StoreStatusCode.NO_MENU_GROUP));

        StoreMenuOptionGroup optionGroup = storeMapper.mapToMenuOptionGroupEntity(optionGroupReq, storeMenu);

        return storeOptionGroupRepository.save(optionGroup).getId();
    }

    @Transactional
    public Long createOption(Long optionGroupId, MenuPostDto.OptionReq optionReq) {

        StoreMenuOptionGroup storeMenuOptionGroup = storeOptionGroupRepository.findById(optionGroupId)
                .orElseThrow(() -> new StoreException(StoreStatusCode.NO_MENU_GROUP));

        StoreMenuOption menuGroup = storeMapper.mapPostDtoToMenuOptionEntity(optionReq, storeMenuOptionGroup);

        return storeOptionRepository.save(menuGroup).getId();
    }

}
