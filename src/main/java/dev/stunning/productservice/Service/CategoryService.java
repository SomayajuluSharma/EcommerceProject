package dev.stunning.productservice.Service;

import dev.stunning.productservice.models.Category;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CategoryService {

    List<String> getAllCategories();
    Category getProductsInCategory(@PathVariable("categoryId") String categoryName);
}
