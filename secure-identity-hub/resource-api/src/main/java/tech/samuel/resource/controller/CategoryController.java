package tech.samuel.resource.controller;

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
import jakarta.validation.constraints.NotBlank;
import tech.samuel.resource.controller.dto.CategoryDto;
import tech.samuel.resource.controller.mapper.ApiMapper;
import tech.samuel.resource.model.Category;
import tech.samuel.resource.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;
    public CategoryController(CategoryService service) { this.service = service; }

    @GetMapping
    public List<CategoryDto> list() {
        return service.findAll().stream().map(ApiMapper::toDto).toList();
    }

    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody @Valid CreateCategory req) {
        Category c = new Category();
        c.setName(req.name());
        Category saved = service.create(c);
        return ResponseEntity
                .created(URI.create("/api/categories/" + saved.getId()))
                .body(ApiMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public CategoryDto update(@PathVariable Long id, @RequestBody @Valid CreateCategory req) {
        Category payload = new Category();
        payload.setName(req.name());
        return ApiMapper.toDto(service.update(id, payload));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // DTO de entrada
    public record CreateCategory(@NotBlank String name) {}
}
