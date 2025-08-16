package tech.samuel.resource.controller.dto;

import java.math.BigDecimal;

public record ProductDto(Long id, String name, BigDecimal price, Long categoryId) {}
