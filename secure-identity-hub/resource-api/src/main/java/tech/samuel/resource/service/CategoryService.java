package tech.samuel.resource.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.samuel.resource.model.Category;
import tech.samuel.resource.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository repo;

    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public List<Category> findAll() {
        return repo.findAll();
    }

    public Category findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }

    @Transactional
    public Category create(Category c) {
        if (repo.existsByNameIgnoreCase(c.getName())) {
            throw new IllegalStateException("Category already exists");
        }
        return repo.save(c);
    }

    @Transactional
    public Category update(Long id, Category incoming) {
        Category c = findById(id);
        c.setName(incoming.getName());
        return repo.save(c);
    }

    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
