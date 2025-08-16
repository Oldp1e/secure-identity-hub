package tech.samuel.resource.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import tech.samuel.resource.controller.dto.ProductDto;
import tech.samuel.resource.model.Product;
import tech.samuel.resource.service.ProductService;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void create_returns201_andBody() throws Exception {
        // dado
        var reqJson = """
          {"name":"USB-C Charger 65W","price":149.90,"categoryId":1}
        """;

        // mock do service
        Product persisted = new Product();
        persisted.setId(42L);
        persisted.setName("USB-C Charger 65W");
        persisted.setPrice(new BigDecimal("149.90"));
        Mockito.when(productService.create(Mockito.any(Product.class), Mockito.eq(1L)))
               .thenReturn(persisted);

        // quando / então
        mvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(reqJson))
           .andExpect(status().isCreated())
           .andExpect(header().string("Location", "/api/products/42"))
           .andExpect(jsonPath("$.id").value(42))
           .andExpect(jsonPath("$.name").value("USB-C Charger 65W"))
           .andExpect(jsonPath("$.price").value(149.90));
    }
}
