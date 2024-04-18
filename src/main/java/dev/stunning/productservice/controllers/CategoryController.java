package dev.stunning.productservice.controllers;

import dev.stunning.productservice.Service.CategoryService;
import dev.stunning.productservice.dtos.CategoryDto;
import dev.stunning.productservice.dtos.GetSingleCategory;
import dev.stunning.productservice.models.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController

public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/products/categories")
    public CategoryDto getAllCategories() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategories(categoryService.getAllCategories());
    //    ResponseEntity<CategoryDto> responseEntity = new ResponseEntity(categoryDto, HttpStatus.OK);
        return categoryDto;
    }

    @GetMapping("/products/category/{categoryName}")
    public ResponseEntity<GetSingleCategory> getProductsInCategory(@PathVariable("categoryName") String categoryName) {
        GetSingleCategory responseDto = new GetSingleCategory();
        responseDto.setCategory(categoryService.getProductsInCategory(categoryName));
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }
}
