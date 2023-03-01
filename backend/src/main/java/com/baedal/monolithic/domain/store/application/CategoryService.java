package com.baedal.monolithic.domain.store.application;

import com.baedal.monolithic.domain.store.dto.StoreCategoryFindAllDto;
import com.baedal.monolithic.domain.store.repository.StoreCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final StoreCategoryRepository storeCategoryRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<StoreCategoryFindAllDto> findAllCategory() {
        return storeCategoryRepository.findAll()
                .stream()
                .map(category -> modelMapper.map(category, StoreCategoryFindAllDto.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Long countCategory() {
        return storeCategoryRepository.count();
    }
}
