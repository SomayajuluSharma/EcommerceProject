package dev.stunning.productservice.controllers;

import dev.stunning.productservice.Service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/products/categories")
    public String getAllCategories() {
        return "Getting All Categories";
    }

    @GetMapping("/products/category/{categoryId}")
    public String getProductsInCategory(@PathVariable("categoryId") Long categoryId) {
        return "Getting Products in Category " + categoryId;
    }

}
