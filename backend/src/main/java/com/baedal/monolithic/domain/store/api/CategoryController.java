package com.baedal.monolithic.domain.store.api;

import com.baedal.monolithic.domain.store.application.CategoryService;
import com.baedal.monolithic.domain.store.dto.StoreCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stores/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<StoreCategoryDto.GetRes> findAll () {

        return ResponseEntity.ok(
                new StoreCategoryDto.GetRes(
                        categoryService.countCategory(),
                        categoryService.findAllCategory()
                )
        );
    }

}
