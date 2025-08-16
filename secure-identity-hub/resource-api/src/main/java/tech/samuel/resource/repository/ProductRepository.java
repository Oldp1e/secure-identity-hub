package tech.samuel.resource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.samuel.resource.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
}
