package dev.stunning.productservice.controllers;

import dev.stunning.productservice.Service.ProductService;
import dev.stunning.productservice.dtos.ProductDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String getAllProducts() {
        return "Getting All Products";
    }

    @GetMapping("/products/{productId}")
    public String getSingleProoduct(@PathVariable("productId") Long productId) {
        return "Getting Single Product " + productId;
    }

    @PostMapping("/products")
    public String addProduct(@RequestBody ProductDto productDto ) {
        return "Adding new Product" + productDto;
    }

    @PutMapping("/products/{productId}")
    public String updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productDto){
        return "Updating Product " + productId + " with " + productDto;
    }

    @DeleteMapping("/products/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId) {
        return "Deleting Product " + productId;
    }
}
