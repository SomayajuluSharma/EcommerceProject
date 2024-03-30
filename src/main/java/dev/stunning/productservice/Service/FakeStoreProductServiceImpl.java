package dev.stunning.productservice.Service;

import dev.stunning.productservice.dtos.ProductDto;
import org.springframework.stereotype.Service;

@Service
public class FakeStoreProductServiceImpl implements ProductService{

    @Override
    public String getAllProducts() {
        return null;
    }

    @Override
    public String getSingleProoduct(Long productId) {
        return null;
    }

    @Override
    public String addProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public String updateProduct(Long productId, ProductDto productDto) {
        return null;
    }

    @Override
    public String deleteProduct(Long productId) {
        return null;
    }
}
