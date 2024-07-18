package dev.stunning.productservice.controllers;

import dev.stunning.productservice.Service.SearchService;
import dev.stunning.productservice.dtos.ProductDto;
import dev.stunning.productservice.dtos.SearchRequestDto;
import dev.stunning.productservice.models.Category;
import dev.stunning.productservice.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }


    @PostMapping("/")
    public List<ProductDto> searchProducts(@RequestBody SearchRequestDto searchRequestDto) {
        List<Product> result =  searchService.searchProduct(searchRequestDto.getQuery());
        List<ProductDto> shareableResult = new LinkedList<>();
        for(Product product : result){
            shareableResult.add(getProduct(product));
        }
        return shareableResult;
    } 
    private ProductDto getProduct(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
//        productDto.setCategory(product.getCategory().getName());
        productDto.setImage(product.getImageUrl());
        return productDto;
    }
}
