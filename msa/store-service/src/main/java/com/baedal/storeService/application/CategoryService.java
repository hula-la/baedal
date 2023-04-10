package com.baedal.storeService.application;

import com.baedal.storeService.dto.StoreCategoryDto;
import com.baedal.storeService.repository.StoreCategoryRepository;
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
    public List<StoreCategoryDto.Info> findAllCategory() {
        return storeCategoryRepository.findAll()
                .stream()
                .map(category -> modelMapper.map(category, StoreCategoryDto.Info.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Long countCategory() {
        return storeCategoryRepository.count();
    }
}
