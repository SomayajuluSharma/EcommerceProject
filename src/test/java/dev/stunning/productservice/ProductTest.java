package dev.stunning.productservice;

import dev.stunning.productservice.Service.SelfProductService;
import dev.stunning.productservice.models.Category;
import dev.stunning.productservice.models.Product;
import dev.stunning.productservice.repositories.CategoryRepository;
import dev.stunning.productservice.repositories.ProductDBDto;
import dev.stunning.productservice.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SelfProductService selfProductService;

    @Test
    void testFetchingType(){
        Category category = new Category();
        category.setName("Electronics");
        Category savedCategory = categoryRepository.save(category);

        Product product = new Product();
        product.setPrice(100);
        product.setTitle("Iphone");
        product.setCategory(category);
        productRepository.save(product);

        Product product2 = new Product();
        product2.setPrice(200);
        product2.setTitle("Nokia");
        product2.setCategory(category);
        productRepository.save(product2);
    }

    @Test
    @Transactional
    void fetchTypesTest() {
        Product product = productRepository.findProductById(452L);
        System.out.println("Fetch Product");
        product.getCategory();
    }
    @Test
    void deleteProduct(){
        productRepository.deleteById(602L);
    }
    @Test
    void productDBTest(){
        List<ProductDBDto> productDBDto = productRepository.bringProductById(602L);
    }

    @Test
    void gettingProduct(){
        Optional<Product> p = selfProductService.getSingleProduct(1L);
        System.out.println(p);
    }
    @Test
    void addNewProduct(){
        Category category = new Category();
        category.setName("Electronics");

        Product product = new Product();
        product.setPrice(200);
        product.setTitle("Iphone12");
        product.setDescription("Iphone 12 is nice");
        product.setCategory(category);
        selfProductService.addNewProduct(product);
    }
    @Test
    @Transactional
    void getCategory(){
        Category category = categoryRepository.findByName("Electronics");
        List<Product> products = category.getProducts();
        System.out.println(products);
    }
}
