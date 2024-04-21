package dev.stunning.productservice.Service;

import dev.stunning.productservice.Exceptions.NotFoundException;
import dev.stunning.productservice.dtos.ProductDto;
import dev.stunning.productservice.models.Product;
import dev.stunning.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelfProductService implements ProductService{

   private  ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
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
