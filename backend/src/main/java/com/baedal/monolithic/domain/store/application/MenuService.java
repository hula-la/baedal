package com.baedal.monolithic.domain.store.application;

import com.baedal.monolithic.domain.order.dto.OrderDto;
import com.baedal.monolithic.domain.store.entity.StoreMenu;
import com.baedal.monolithic.domain.store.exception.StoreException;
import com.baedal.monolithic.domain.store.exception.StoreStatusCode;
import com.baedal.monolithic.domain.store.repository.StoreMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final StoreMenuRepository storeMenuRepository;


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

}
