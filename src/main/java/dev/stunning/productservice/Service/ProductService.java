package dev.stunning.productservice.Service;

import dev.stunning.productservice.dtos.ProductDto;
import dev.stunning.productservice.models.Product;

import java.util.List;
import java.util.Optional;


public interface ProductService {

    List<Product> getAllProducts();
    Optional<Product> getSingleProduct(Long productId);
    Product addNewProduct(ProductDto product);
    Product updateProduct(Long productId,Product product);
    Product replaceProduct(Long ProductId, Product product);
    Product deleteProduct(Long productId);
}
