package com.baedal.storeService.api;

import com.baedal.storeService.application.CategoryService;
import com.baedal.storeService.dto.StoreCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
