package tech.samuel.resource.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import tech.samuel.resource.model.Category;

import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("test")
@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repo;

    @Test
    void existsByNameIgnoreCase_returnsTrue_whenNameExists() {
        Category c = new Category();
        c.setName("Eletrônicos");
        repo.save(c);

        boolean exists = repo.existsByNameIgnoreCase("eletrônicos");
        assertThat(exists).isTrue();
    }

    @Test
    void save_persistsAndGeneratesId() {
        Category c = new Category();
        c.setName("Games");
        Category saved = repo.save(c);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("Games");
    }
}
