package dev.stunning.productservice.controllers;

import dev.stunning.productservice.dtos.ErrorResponseDto;
import dev.stunning.productservice.Exceptions.NotFoundException;
import dev.stunning.productservice.Service.ProductService;
import dev.stunning.productservice.dtos.GetSingleCategory;
import dev.stunning.productservice.dtos.GetSingleProductResponseDto;
import dev.stunning.productservice.dtos.ProductDto;
import dev.stunning.productservice.models.Category;
import dev.stunning.productservice.models.Product;
import dev.stunning.productservice.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@RestController
@RequestMapping("/products")
public class ProductController {


    private ProductService productService;
    private ProductRepository productRepository;

    public ProductController(ProductService productService,ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProoduct(@PathVariable("productId") Long productId) throws NotFoundException{
        //GetSingleProductResponseDto responseDto = new GetSingleProductResponseDto();
        //responseDto.setProduct(productService.getSingleProduct(productId));

        MultiValueMap<String,String> headers = new LinkedMultiValueMap();

        headers.add("auth-token","noaccess4uheyhey");

        Optional<Product> productOptional = productService.getSingleProduct(productId);
        if(productOptional.isEmpty()){
            throw new NotFoundException("Product not found");
        }
        ResponseEntity<Product> responseEntity = new ResponseEntity(
                productService.getSingleProduct(productId),
                headers,
                HttpStatus.NOT_FOUND
        );

        return responseEntity;
    }

    @PostMapping("")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDto product) {

        MultiValueMap<String,String> headers = new LinkedMultiValueMap();
        headers.add("auth-token","SuccessFullyAddedProduct");

        //Product newProduct = productService.addNewProduct(product);
        Product newProduct = new Product();
        newProduct.setId(product.getId());
        newProduct.setDescription(product.getDescription());
        newProduct.setTitle(product.getTitle());
        newProduct.setPrice(product.getPrice());
        newProduct.setImageUrl(product.getImage());
        newProduct = productRepository.save(newProduct);
        ResponseEntity<Product> responseEntity = new ResponseEntity(
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
        //return null;
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
       // return productService.deleteProduct(productId);
        return null;
    }
}
