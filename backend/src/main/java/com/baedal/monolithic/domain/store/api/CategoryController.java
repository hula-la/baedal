package com.baedal.monolithic.domain.store.api;

import com.baedal.monolithic.domain.store.application.CategoryService;
import com.baedal.monolithic.domain.store.dto.StoreCategoryFindAllDto;
import com.baedal.monolithic.domain.store.dto.StoreFindAllDto;
import com.baedal.monolithic.domain.store.entity.StoreCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<StoreCategoryFindAllRes> findAll () {
        return ResponseEntity.ok(
                new StoreCategoryFindAllRes(
                        categoryService.countCategory(),
                        categoryService.findAllCategory()
                )
        );
    }

    @AllArgsConstructor
    @Getter
    private static class StoreCategoryFindAllRes {

        private Long results;
        private List<StoreCategoryFindAllDto> categories;

    }

}
