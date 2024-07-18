package dev.stunning.productservice.Service;

import dev.stunning.productservice.Exceptions.NotFoundException;
import dev.stunning.productservice.models.Product;
import dev.stunning.productservice.repositories.products.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class SelfProductService implements ProductService{

   private  ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Page<Product> getProducts(int numberOfProducts, int offset){
        Page<Product> products = productRepository.findAll(PageRequest.of( (offset/numberOfProducts), numberOfProducts,
                Sort.by("price").descending()
                        .and(Sort.by("title").ascending())));

        return products;
    }

    @Override
    public Optional<Product> getSingleProduct(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Product addNewProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product product) throws NotFoundException{
        Product productToUpdate = productRepository.findById(productId).orElseThrow(()-> new NotFoundException("Product not found"));

        productToUpdate.setId(product.getId());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setCategory(product.getCategory());
        productToUpdate.setTitle(product.getTitle());
        return productRepository.save(productToUpdate);

    }

    @Override
    public Product replaceProduct(Long productId, Product newProduct) {

            return productRepository.save(newProduct);
    }

    @Override
    public Product deleteProduct(Long productId) throws NotFoundException{
        Product productToDelete = productRepository.findById(productId).orElseThrow(()-> new NotFoundException("Product not found"));
        productRepository.deleteProductById(productId);
        return productToDelete;
    }
}
