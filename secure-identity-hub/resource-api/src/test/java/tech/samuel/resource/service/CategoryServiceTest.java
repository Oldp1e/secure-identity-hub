package tech.samuel.resource.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import tech.samuel.resource.model.Category;
import tech.samuel.resource.repository.CategoryRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@Import(CategoryService.class) // injeta o serviço usando o repo real (H2)
class CategoryServiceTest {

    @Autowired
    private CategoryService service;

    @Autowired
    private CategoryRepository repo; // opcional, só se precisar assert extra

    @Test
    void create_savesCategory_whenNameIsUnique() {
        Category c = new Category();
        c.setName("Hardware");

        Category saved = service.create(c);
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void create_throws_whenNameDuplicated() {
        Category c1 = new Category(); c1.setName("TV");
        service.create(c1);

        Category c2 = new Category(); c2.setName("TV");
        assertThatThrownBy(() -> service.create(c2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("already");
    }
}
