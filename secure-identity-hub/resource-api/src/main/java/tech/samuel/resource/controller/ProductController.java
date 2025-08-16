package tech.samuel.resource.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tech.samuel.resource.controller.dto.ProductDto;
import tech.samuel.resource.controller.mapper.ApiMapper;
import tech.samuel.resource.model.Product;
import tech.samuel.resource.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;
    public ProductController(ProductService service) { this.service = service; }

    @GetMapping
    public List<ProductDto> list() {
        return service.findAll().stream().map(ApiMapper::toDto).toList();
    }

    @GetMapping("/by-category/{categoryId}")
    public List<ProductDto> listByCategory(@PathVariable Long categoryId) {
        return service.findByCategory(categoryId).stream().map(ApiMapper::toDto).toList();
    }

    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody @Valid CreateProduct req) {
        Product p = new Product();
        p.setName(req.name());
        p.setPrice(req.price());
        Product saved = service.create(p, req.categoryId());
        return ResponseEntity
                .created(URI.create("/api/products/" + saved.getId()))
                .body(ApiMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public ProductDto update(@PathVariable Long id, @RequestBody @Valid CreateProduct req) {
        Product payload = new Product();
        payload.setName(req.name());
        payload.setPrice(req.price());
        return ApiMapper.toDto(service.update(id, payload, req.categoryId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // DTO de entrada
    public record CreateProduct(
            @NotBlank String name,
            @NotNull @DecimalMin(value = "0.00") BigDecimal price,
            @NotNull Long categoryId
    ) {}
}
