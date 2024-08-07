package dev.stunning.productservice.Service;

import dev.stunning.productservice.dtos.FakeStoreProductDto;
import dev.stunning.productservice.dtos.ProductDto;
import dev.stunning.productservice.models.Category;
import dev.stunning.productservice.models.Product;
import dev.stunning.productservice.Config.RedisConfig;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.*;

@Service
//@Primary
public class FakeStoreProductServiceImpl implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;

    private RedisTemplate<Long,Object> redisTemplate;

    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder, RedisTemplate<Long, Object> redisTemplate){
        this.restTemplateBuilder = restTemplateBuilder;
        this.redisTemplate = redisTemplate;
    }


    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod,String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {

        RestTemplate restTemplate = restTemplateBuilder.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();

        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto productDto){
        dev.stunning.productservice.models.Product product = new dev.stunning.productservice.models.Product();
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
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> l = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> answer = new ArrayList<>();
        for(FakeStoreProductDto productDto : l.getBody()){
            answer.add(convertFakeStoreProductDtoToProduct(productDto));
        }
        return answer;
    }

    @Override
    public Optional<Product> getSingleProduct(Long productId) {
        FakeStoreProductDto fakeStoreProductDto = (FakeStoreProductDto) redisTemplate.opsForHash().get(productId,"PRODUCTS");
        if(fakeStoreProductDto != null){
            return Optional.of(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity
                ("https://fakestoreapi.com/products/{id}",
                        FakeStoreProductDto.class, productId);
        //(url,returnType,params_in_url)
        FakeStoreProductDto productDto = response.getBody();
        redisTemplate.opsForHash().put(productId,"PRODUCTS",productDto);
        if(productDto == null){
            return Optional.empty();
        }

        return Optional.of(convertFakeStoreProductDtoToProduct(productDto));
    }

    @Override
    public Page<Product> getProducts(int numberOfProducts, int offset){



        return null;
    }

    @Override
    public Product addNewProduct(Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                product,
                FakeStoreProductDto.class
        );
        FakeStoreProductDto productDto = response.getBody();
        return convertFakeStoreProductDtoToProduct(productDto);
    }

    @Override
    public Product updateProduct(Long productId, Product product){

        RestTemplate restTemplate = restTemplateBuilder.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setTitle(product.getTitle());


        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity(
                HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDto,
                FakeStoreProductDto.class,
                productId
        );


        return convertFakeStoreProductDtoToProduct(fakeStoreProductDtoResponseEntity.getBody());
    }

    @Override
    public Product replaceProduct(Long ProductId, dev.stunning.productservice.models.Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setTitle(product.getTitle());

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity(
                HttpMethod.PUT,
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDto,
                FakeStoreProductDto.class,
                ProductId
        );
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDtoResponseEntity.getBody());
    }

    @Override
    public Product deleteProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response  = requestForEntity(
                HttpMethod.DELETE,
                "https://fakestoreapi.com/products/{id}",
                null,
                FakeStoreProductDto.class,
                productId
        );
        return convertFakeStoreProductDtoToProduct(response.getBody());
    }
}
