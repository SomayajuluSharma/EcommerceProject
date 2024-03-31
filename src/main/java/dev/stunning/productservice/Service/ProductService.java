package dev.stunning.productservice.Service;

import dev.stunning.productservice.dtos.ProductDto;
import dev.stunning.productservice.models.Product;

import java.util.List;


public interface ProductService {

    List<Product> getAllProducts();
    Product getSingleProduct(Long productId);
    Product addNewProduct(ProductDto product);
    Product updateProduct(Long productId,Product product);
    Product deleteProduct(Long productId);
}
