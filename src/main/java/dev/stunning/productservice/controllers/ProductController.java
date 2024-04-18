package dev.stunning.productservice.controllers;

import dev.stunning.productservice.Service.ProductService;
import dev.stunning.productservice.dtos.GetSingleProductResponseDto;
import dev.stunning.productservice.dtos.ProductDto;
import dev.stunning.productservice.models.Category;
import dev.stunning.productservice.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
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

    @PostMapping()
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

    @PatchMapping("/{productId}")
    public Product updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setCategory(new Category());
        product.getCategory().setName(productDto.getCategory());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());

        return productService.updateProduct(productId, product);
    }

    @PutMapping("/{productId}")
    public Product replaceProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setId(productId);
        product.setCategory(new Category());
        product.getCategory().setName(productDto.getCategory());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());

        return productService.replaceProduct(productId, product);
    }

    @DeleteMapping("/{productId}")
    public Product deleteProduct(@PathVariable("productId") Long productId) {
        Product product = new Product();
        product.setId(productId);
        return productService.deleteProduct(productId);
    }
}
