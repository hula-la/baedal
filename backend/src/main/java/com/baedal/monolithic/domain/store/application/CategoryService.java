package com.baedal.monolithic.domain.store.application;

import com.baedal.monolithic.domain.store.dto.StoreCategoryDto;
import com.baedal.monolithic.domain.store.repository.StoreCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final StoreCategoryRepository storeCategoryRepository;
    private final StoreMapper storeMapper;

    @Transactional(readOnly = true)
    @Cacheable(value = "categories")
    public List<StoreCategoryDto.Info> findAllCategory() {
        return storeCategoryRepository.findAll()
                .stream()
                .map(storeMapper::mapToCategoryDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "numOfCategories")
    public Long countCategory() {
        return storeCategoryRepository.count();
    }
}
