package dev.stunning.productservice.repositories.products;

import dev.stunning.productservice.models.Product;
import dev.stunning.productservice.repositories.ProductDBDto;
import dev.stunning.productservice.repositories.Queries;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>{
    Product save(Product product);
    Product findProductById(Long id);
    Product deleteProductById(Long id);

    @Query(value = Queries.Bring_Product_By_id, nativeQuery = true)
    List<ProductDBDto> bringProductById(Long id);

    Page<Product> findAll(Pageable pageable);
    List<Product> findByTitleEquals(String title);
}
