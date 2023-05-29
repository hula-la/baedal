package com.baedal.monolithic.domain.store.application;

import com.baedal.monolithic.domain.store.dto.MenuDto;
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

    private MenuDto.Group mapToGroupDto(StoreMenuGroup storeMenuGroup){

        return  MenuDto.Group.builder()
                .id(storeMenuGroup.getId())
                .name(storeMenuGroup.getName())
                .detail(storeMenuGroup.getDetail())
                .menus(storeMenuGroup.getMenus().stream()
                        .map(this::mapToMenuDto)
                        .collect(Collectors.toList()))
                .build();
    }

    private MenuDto.Menu mapToMenuDto(StoreMenu storeMenu){

        return  MenuDto.Menu.builder()
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

    private MenuDto.Option mapToOptionDto(StoreMenuOption option){

        return  MenuDto.Option.builder()
                .id(option.getId())
                .name(option.getName())
                .price(option.getPrice())
                .build();
    }

    private MenuDto.OptionGroup mapToOptionGroupDto(StoreMenuOptionGroup optionGroup){

        return  MenuDto.OptionGroup.builder()
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

}
