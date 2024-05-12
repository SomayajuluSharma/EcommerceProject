package dev.stunning.productservice.Service;

import dev.stunning.productservice.dtos.FakeStoreProductDto;
import dev.stunning.productservice.models.Category;
import dev.stunning.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//@Service
//@Primary
public class FakeStoreCategoryServiceImpl implements CategoryService{

    private RestTemplateBuilder restTemplateBuilder;

    FakeStoreCategoryServiceImpl(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        return product;
    }


    @Override
    public List<String> getAllCategories() {
       RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<String[]> l = restTemplate.getForEntity("https://fakestoreapi.com/products/categories",String[].class);
        List<String> answer = new ArrayList<>();
        for(String category : l.getBody()) {
            answer.add(category);
        }
        return answer;
    }

    @Override
    public Category getProductsInCategory(String categoryName) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> l = restTemplate.getForEntity("https://fakestoreapi.com/products/category/"+categoryName,FakeStoreProductDto[].class);
        List<Product> answer = new ArrayList<>();
        for(FakeStoreProductDto product : l.getBody()){
            answer.add(convertFakeStoreProductDtoToProduct(product));
        }
        Category category = new Category();
        category.setName(categoryName);
        category.setProducts(answer);
        return category;
    }
}
