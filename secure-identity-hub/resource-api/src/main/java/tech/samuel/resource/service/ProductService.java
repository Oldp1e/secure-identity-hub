package tech.samuel.resource.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.samuel.resource.model.Category;
import tech.samuel.resource.model.Product;
import tech.samuel.resource.repository.CategoryRepository;
import tech.samuel.resource.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository products;
    private final CategoryRepository categories;

    public ProductService(ProductRepository products, CategoryRepository categories) {
        this.products = products;
        this.categories = categories;
    }

    public List<Product> findAll() {
        return products.findAll();
    }

    public List<Product> findByCategory(Long categoryId) {
        return products.findByCategoryId(categoryId);
    }

    public Product findById(Long id) {
        return products.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    @Transactional
    public Product create(Product p, Long categoryId) {
        Category c = categories.findById(categoryId).orElseThrow(() -> new IllegalArgumentException("Category not found"));
        p.setCategory(c);
        return products.save(p);
    }

    @Transactional
    public Product update(Long id, Product incoming, Long categoryId) {
        Product p = findById(id);
        p.setName(incoming.getName());
        p.setPrice(incoming.getPrice());
        if (categoryId != null) {
            Category c = categories.findById(categoryId).orElseThrow(() -> new IllegalArgumentException("Category not found"));
            p.setCategory(c);
        }
        return products.save(p);
    }

    @Transactional
    public void delete(Long id) {
        products.deleteById(id);
    }
}
