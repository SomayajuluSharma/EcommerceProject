package dev.stunning.productservice.Service;

import dev.stunning.productservice.dtos.ProductDto;
import org.springframework.web.bind.annotation.*;


public interface ProductService {

    String getAllProducts();
    String getSingleProoduct(@PathVariable("productId") Long productId);
    String addProduct(@RequestBody ProductDto productDto);
    String updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productDto);
    String deleteProduct(@PathVariable("productId") Long productId);
}
