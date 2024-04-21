package dev.stunning.productservice.repositories;

import dev.stunning.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>{
    Product save(Product product);
    Product findProductById(Long id);
    Product deleteProductById(Long id);
    Product updateProductById(Long id);

    @Query(value = Queries.Bring_Product_By_id, nativeQuery = true)
    List<ProductDBDto> bringProductById(Long id);
}
