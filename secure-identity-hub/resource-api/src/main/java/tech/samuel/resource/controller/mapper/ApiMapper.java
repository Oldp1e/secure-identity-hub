package tech.samuel.resource.controller.mapper;

import tech.samuel.resource.controller.dto.CategoryDto;
import tech.samuel.resource.controller.dto.ProductDto;
import tech.samuel.resource.model.Category;
import tech.samuel.resource.model.Product;

public final class ApiMapper {

    private ApiMapper() {}

    public static CategoryDto toDto(Category c) {
        return new CategoryDto(c.getId(), c.getName());
    }

    public static ProductDto toDto(Product p) {
        Long catId = (p.getCategory() != null ? p.getCategory().getId() : null);
        return new ProductDto(p.getId(), p.getName(), p.getPrice(), catId);
    }
}
