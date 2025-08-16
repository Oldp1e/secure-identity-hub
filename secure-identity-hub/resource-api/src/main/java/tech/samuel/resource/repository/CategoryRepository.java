package tech.samuel.resource.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.samuel.resource.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByNameIgnoreCase(String name);
}
