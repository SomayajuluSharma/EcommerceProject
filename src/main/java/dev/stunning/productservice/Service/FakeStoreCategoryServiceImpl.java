package dev.stunning.productservice.Service;

import org.springframework.stereotype.Service;

@Service
public class FakeStoreCategoryServiceImpl implements CategoryService{
    @Override
    public String getAllCategories() {
        return null;
    }

    @Override
    public String getProductsInCategory(Long categoryId) {
        return null;
    }
}
