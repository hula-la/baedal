package com.baedal.monolithic.domain.store.application;

import com.baedal.monolithic.domain.store.dto.MenuGetDto;
import com.baedal.monolithic.domain.store.dto.MenuPostDto;
import com.baedal.monolithic.domain.store.dto.StoreCategoryDto;
import com.baedal.monolithic.domain.store.dto.StoreDto;
import com.baedal.monolithic.domain.store.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
class StoreMapper {

    protected StoreCategoryDto.Info mapToCategoryDto(StoreCategory storeCategory){

        return  StoreCategoryDto.Info.builder()
                .id(storeCategory.getId())
                .name(storeCategory.getName())
                .build();
    }

    protected StoreDto.SummarizedInfo mapToSummarizedStoreDto(Store store){

        return  StoreDto.SummarizedInfo.builder()
                .id(store.getId())
                .name(store.getName())
                .rating(store.getRating())
                .img(store.getImg())
                .build();
    }

    protected StoreDto.DetailedInfo mapToDetailedStoreDto(Store store,
                                                          String ownerName){

        return  StoreDto.DetailedInfo.builder()
                .id(store.getId())
                .ownerName(ownerName)
                .name(store.getName())
                .minPrice(store.getMinPrice())
                .recentOrder(store.getRecentOrder())
                .recentReview(store.getRecentReview())
                .heartNum(store.getHeartNum())
                .rating(store.getRating())
                .notice(store.getNotice())
                .time(store.getTime())
                .closedDay(store.getClosedDay())
                .tel(store.getTel())
                .deliveryRegion(store.getDeliveryRegion())
                .address(store.getAddressDetail())
                .info(store.getInfo())
                .storeStatus(store.getStoreStatus())
                .img(store.getImg())
                .storeMenuGroups(store.getMenuGroups().stream()
                        .map(this::mapToGroupDto)
                        .collect(Collectors.toList()))
                .build();
    }

    private MenuGetDto.MenuGroup mapToGroupDto(StoreMenuGroup storeMenuGroup){

        return  MenuGetDto.MenuGroup.builder()
                .id(storeMenuGroup.getId())
                .name(storeMenuGroup.getName())
                .detail(storeMenuGroup.getDetail())
                .menus(storeMenuGroup.getMenus().stream()
                        .map(this::mapToMenuDto)
                        .collect(Collectors.toList()))
                .build();
    }

    private MenuGetDto.Menu mapToMenuDto(StoreMenu storeMenu){

        return  MenuGetDto.Menu.builder()
                .id(storeMenu.getId())
                .name(storeMenu.getName())
                .price(storeMenu.getPrice())
                .img(storeMenu.getImg())
                .expIntro(storeMenu.getExpIntro())
                .expDetail(storeMenu.getExpDetail())
                .status(storeMenu.getStatus())
                .optionGroup(storeMenu.getOptionGroups().stream()
                        .map(this::mapToOptionGroupDto)
                        .collect(Collectors.toList()))
                .build();
    }

    private MenuGetDto.Option mapToOptionDto(StoreMenuOption option){

        return  MenuGetDto.Option.builder()
                .id(option.getId())
                .name(option.getName())
                .price(option.getPrice())
                .build();
    }

    private MenuGetDto.OptionGroup mapToOptionGroupDto(StoreMenuOptionGroup optionGroup){

        return  MenuGetDto.OptionGroup.builder()
                .id(optionGroup.getId())
                .name(optionGroup.getName())
                .isRadio(optionGroup.isRadio())
                .min(optionGroup.getMin())
                .max(optionGroup.getMax())
                .options(optionGroup.getOptions().stream()
                        .map(this::mapToOptionDto)
                        .collect(Collectors.toList()))
                .build();
    }

    protected Store mapToEntity(Long ownerId, StoreDto.PostPutReq storePostReq){

        return  Store.builder()
                .ownerId(ownerId)
                .categoryId(storePostReq.getCategoryId())
                .name(storePostReq.getName())
                .minPrice(storePostReq.getMinPrice())
                .time(storePostReq.getTime())
                .closedDay(storePostReq.getClosedDay())
                .tel(storePostReq.getTel())
                .addressId(storePostReq.getAddressId())
                .deliveryRegion(storePostReq.getDeliveryRegion())
                .addressDetail(storePostReq.getAddressDetail())
                .img(storePostReq.getImg())
                .notice(storePostReq.getNotice())
                .info(storePostReq.getInfo())
                .build();
    }

    // 메뉴 엔티티 변환
    protected StoreMenuGroup mapPostDtoToMenuGroupEntity(MenuPostDto.MenuGroupReq menuGroupDto, Store store){

        return  StoreMenuGroup.builder()
                .name(menuGroupDto.getName())
                .detail(menuGroupDto.getDetail())
                .priority(menuGroupDto.getPriority())
                .store(store)
                .build();
    }

    protected StoreMenu mapPostDtoToMenuEntity(MenuPostDto.MenuReq menuReq, StoreMenuGroup storeMenuGroup){
        return  StoreMenu.builder()
                .name(menuReq.getName())
                .priority(menuReq.getPriority())
                .status(StoreMenuStatus.READY)
                .img(menuReq.getImg())
                .price(menuReq.getPrice())
                .expDetail(menuReq.getExpDetail())
                .expIntro(menuReq.getExpIntro())
                .menuGroup(storeMenuGroup)
                .build();
    }

    protected StoreMenuOptionGroup mapToMenuOptionGroupEntity(MenuPostDto.OptionGroupReq optionGroupReq,
                                                              StoreMenu storeMenu){

        return  StoreMenuOptionGroup.builder()
                .name(optionGroupReq.getName())
                .priority(optionGroupReq.getPriority())
                .isRadio(optionGroupReq.getIsRadio())
                .min(optionGroupReq.getMin())
                .max(optionGroupReq.getMax())
                .menu(storeMenu)
                .build();
    }

    protected StoreMenuOption mapPostDtoToMenuOptionEntity(MenuPostDto.OptionReq optionReq,
                                                           StoreMenuOptionGroup storeMenuOptionGroup){

        return  StoreMenuOption.builder()
                .name(optionReq.getName())
                .price(optionReq.getPrice())
                .priority(optionReq.getPriority())
                .optionGroup(storeMenuOptionGroup)
                .build();
    }



}
