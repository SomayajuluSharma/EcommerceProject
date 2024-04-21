package dev.stunning.productservice.repositories;

import dev.stunning.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  //  Category findCategoryById(Long id);
    Category save(Category category);

    Category findByName(String name);
    List<Category> findAll();

}
