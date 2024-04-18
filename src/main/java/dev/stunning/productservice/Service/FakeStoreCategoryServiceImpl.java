package dev.stunning.productservice.Service;

import dev.stunning.productservice.models.Category;
import dev.stunning.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreCategoryServiceImpl implements CategoryService{

    private RestTemplateBuilder restTemplateBuilder;

    FakeStoreCategoryServiceImpl(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
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
        ResponseEntity<Product[]> l = restTemplate.getForEntity("https://fakestoreapi.com/products/category/"+categoryName,Product[].class);
        List<Product> answer = new ArrayList<>();
        for(Product product : l.getBody()){
            answer.add(product);
        }
        Category category = new Category();
        category.setName(categoryName);
        category.setProducts(answer);
        return category;
    }
}
