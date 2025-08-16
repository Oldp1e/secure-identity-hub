package tech.samuel.resource.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import tech.samuel.resource.model.Category;
import tech.samuel.resource.model.Product;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@Import({ProductService.class, CategoryService.class})
class ProductServiceTest {

    @Autowired
    private ProductService service;

    @Autowired
    private CategoryService categories;

    @Test
    void create_linksProductToCategory() {
        Category cat = new Category(); cat.setName("Periféricos");
        Category savedCat = categories.create(cat);

        Product p = new Product();
        p.setName("Mouse");
        p.setPrice(new BigDecimal("99.90"));

        Product saved = service.create(p, savedCat.getId());
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getCategory().getId()).isEqualTo(savedCat.getId());
    }

    @Test
    void create_throws_whenCategoryNotFound() {
        Product p = new Product();
        p.setName("Teclado");
        p.setPrice(new BigDecimal("199.90"));

        assertThatThrownBy(() -> service.create(p, 999L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Category not found");
    }
}
