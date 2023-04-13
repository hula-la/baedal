package com.baedal.monolithic.domain.store.application;

import com.baedal.monolithic.domain.store.dto.MenuDto;
import com.baedal.monolithic.domain.store.entity.StoreMenu;
import com.baedal.monolithic.domain.store.entity.StoreMenuGroup;
import com.baedal.monolithic.domain.store.entity.StoreMenuOption;
import com.baedal.monolithic.domain.store.entity.StoreMenuOptionGroup;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
class StoreMapper {

    protected MenuDto.SummarizedMenu mapToSummarizedMenuDto(StoreMenu storeMenu){
        return  MenuDto.SummarizedMenu.builder()
                .id(storeMenu.getId())
                .name(storeMenu.getName())
                .price(storeMenu.getPrice())
                .img(storeMenu.getImg())
                .expIntro(storeMenu.getExpIntro())
                .build();
    }

    protected MenuDto.DetailedMenu mapToDetailedMenuDto(StoreMenu storeMenu,
                                                        List<MenuDto.OptionGroup> optionGroup){
        return  MenuDto.DetailedMenu.builder()
                .id(storeMenu.getId())
                .name(storeMenu.getName())
                .price(storeMenu.getPrice())
                .img(storeMenu.getImg())
                .expDetail(storeMenu.getExpDetail())
                .optionGroup(optionGroup)
                .build();
    }

    protected MenuDto.Group mapToGroupDto(StoreMenuGroup group,
                                          List<MenuDto.SummarizedMenu> menus){
        return  MenuDto.Group.builder()
                .id(group.getId())
                .name(group.getName())
                .detail(group.getDetail())
                .menus(menus)
                .build();
    }

    protected MenuDto.Option mapToOptionDto(StoreMenuOption option){
        return  MenuDto.Option.builder()
                .id(option.getId())
                .name(option.getName())
                .price(option.getPrice())
                .build();
    }

    protected MenuDto.OptionGroup mapToOptionGroupDto(StoreMenuOptionGroup optionGroup,
                                                      List<MenuDto.Option> options){
        return  MenuDto.OptionGroup.builder()
                .id(optionGroup.getId())
                .name(optionGroup.getName())
                .isRadio(optionGroup.isRadio())
                .min(optionGroup.getMin())
                .max(optionGroup.getMax())
                .options(options)
                .build();
    }

}
