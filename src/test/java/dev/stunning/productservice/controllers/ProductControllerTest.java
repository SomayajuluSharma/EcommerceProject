package dev.stunning.productservice.controllers;

import dev.stunning.productservice.Exceptions.NotFoundException;
import dev.stunning.productservice.Service.FakeStoreProductServiceImpl;
import dev.stunning.productservice.Service.ProductService;
import dev.stunning.productservice.Service.SelfProductService;
import dev.stunning.productservice.models.Product;
import jakarta.transaction.Transactional;
import org.apache.hc.client5.http.HttpResponseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.awaitility.Awaitility.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private FakeStoreProductServiceImpl productService;

    @Test
    void getSingleProductsShouldReturnProduct() throws NotFoundException{

        Long productId = 1L;
        Product p1 = new Product();
        p1.setId(productId);
        p1.setPrice(109.95);

        when(productService.getSingleProduct(productId)).thenReturn(Optional.of(p1));
        ResponseEntity<Product> product = productController.getSingleProduct(productId);


        assertEquals(109.95, Objects.requireNonNull(product.getBody()).getPrice());
    }


  /*  void getListOfProductsShouldReturnListOfProducts() {
        List<Product> products = new ArrayList<>();
        Product p1 = new Product();
        p1.setId(1L);
        p1.setPrice(109.95);
        Product p2 = new Product();
        p2.setId(2L);
        p2.setPrice(209.95);
        products.add(p1);
        products.add(p2);

        when(productService.getAllProducts()).thenReturn(products);
        assertThat(2).isEqualTo(productController.getAllProducts().size());
    }*/

}