package dev.stunning.productservice.Service;

import dev.stunning.productservice.models.Category;
import dev.stunning.productservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelfCategoryService implements CategoryService{

    private CategoryRepository categoryRepository;
    @Override
    public List<String> getAllCategories() {
        return categoryRepository.findAll().stream().map(Category::getName).toList();
    }

    @Override
    public Category getProductsInCategory(String categoryName) {
        return categoryRepository.findByName(categoryName);
    }
}
