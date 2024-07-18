package dev.stunning.productservice.Service;


import dev.stunning.productservice.dtos.ProductDto;
import dev.stunning.productservice.models.Product;
import dev.stunning.productservice.repositories.products.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private ProductRepository productRepository;

    public SearchService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> searchProduct(String query){
        return productRepository.findByTitleEquals(query);
    }

}
