package dev.stunning.productservice.controllers;

import dev.stunning.productservice.Service.ProductService;
import dev.stunning.productservice.dtos.GetSingleProductResponseDto;
import dev.stunning.productservice.dtos.ProductDto;
import dev.stunning.productservice.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<GetSingleProductResponseDto> getSingleProoduct(@PathVariable("productId") Long productId) {
        GetSingleProductResponseDto responseDto = new GetSingleProductResponseDto();
        responseDto.setProduct(productService.getSingleProduct(productId));

        MultiValueMap<String,String> headers = new LinkedMultiValueMap();

        headers.add("auth-token","noaccess4uheyhey");

        ResponseEntity<GetSingleProductResponseDto> responseEntity = new ResponseEntity(
               // productService.getSingleProoduct(productId),
                responseDto,
                headers,
                HttpStatus.OK
        );

        return responseEntity;
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto product) {

        MultiValueMap<String,String> headers = new LinkedMultiValueMap();
        headers.add("auth-token","SuccessFullyAddedProduct");

        Product newProduct = productService.addNewProduct(product);
        ResponseEntity<ProductDto> responseEntity = new ResponseEntity(
                newProduct,
                headers,
                HttpStatus.CREATED
        );
        //Product newProduct = productService.addNewProduct(product);
        //ResponseEntity<Product> responseEntity = new ResponseEntity(
        //        newProduct,
        //        HttpStatus.CREATED
        //);
        return responseEntity;
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
