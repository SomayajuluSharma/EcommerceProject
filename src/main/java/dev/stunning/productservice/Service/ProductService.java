package dev.stunning.productservice.Service;

import dev.stunning.productservice.Exceptions.NotFoundException;
import dev.stunning.productservice.dtos.ProductDto;
import dev.stunning.productservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;


public interface ProductService {

    List<Product> getAllProducts();
    Optional<Product> getSingleProduct(Long productId);
    Product addNewProduct(Product product);
    Product updateProduct(Long productId,Product product) throws NotFoundException;
    Product replaceProduct(Long ProductId, Product product);
    Product deleteProduct(Long productId) throws NotFoundException;
    Page<Product> getProducts(int numberOfProducts, int offset);
}
