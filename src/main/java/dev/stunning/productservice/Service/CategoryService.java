package dev.stunning.productservice.Service;

import org.springframework.web.bind.annotation.PathVariable;

public interface CategoryService {

    String getAllCategories();


    String getProductsInCategory(@PathVariable("categoryId") Long categoryId);
}
