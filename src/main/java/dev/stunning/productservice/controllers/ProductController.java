package dev.stunning.productservice.controllers;

import dev.stunning.productservice.AuthenticationClient.AuthClient;
import dev.stunning.productservice.AuthenticationClient.dtos.Role;
import dev.stunning.productservice.AuthenticationClient.dtos.SessionStatus;
import dev.stunning.productservice.AuthenticationClient.dtos.ValidateTokenResponseDto;
import dev.stunning.productservice.Exceptions.NotFoundException;
import dev.stunning.productservice.Service.ProductService;
import dev.stunning.productservice.dtos.ProductDto;
import dev.stunning.productservice.models.Category;
import dev.stunning.productservice.models.Product;
import dev.stunning.productservice.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {


    private ProductService productService;
    private ProductRepository productRepository;
    private AuthClient authClient;

    public ProductController(ProductService productService,ProductRepository productRepository, AuthClient authClient) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.authClient = authClient;
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts(@Nullable @RequestHeader("AUTH_TOKEN") String token, @Nullable @RequestHeader("User_ID") Long userId) {
//        if (token == null || userId == null) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//        ValidateTokenResponseDto response = authClient.validate(token, userId);
//        if (response.getSessionStatus().equals(SessionStatus.INVALID)) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//
//        boolean isUserAdmin = false;
//        for (Role role : response.getUserDto().getRoles()) {
//            if (role.getName().equals("ADMIN")) {
//                isUserAdmin = true;
//                break;
//            }
//        }w
//        if (!isUserAdmin) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId) throws NotFoundException{
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
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {

        MultiValueMap<String,String> headers = new LinkedMultiValueMap();
        headers.add("auth-token","SuccessFullyAddedProduct");

        Product newProduct = productService.addNewProduct(product);
        //Product newProduct = productService.addNewProduct(product);
        ResponseEntity<Product> responseEntity = new ResponseEntity(
                newProduct,
                HttpStatus.CREATED
        );
        return responseEntity;
        //return null;
    }

    @PatchMapping("/{productId}")
    public Optional<Product> updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productDto) throws NotFoundException{
        Product product = new Product();
        product.setId(productDto.getId());
        product.setCategory(new Category());
        product.getCategory().setName(productDto.getCategory());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());

        Optional<Product> optionalProduct = Optional.ofNullable(productService.updateProduct(productId, product));
        return optionalProduct;
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
