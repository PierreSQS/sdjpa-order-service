package guru.springframework.orderservice.repositories;

import guru.springframework.orderservice.domain.Category;
import guru.springframework.orderservice.domain.ProductStatus;
import guru.springframework.orderservice.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepo;

    @Test
    void testGetProduct1Categories() {
        Product product1 = productRepo.findByDescription("PRODUCT1");

        Set<Category> prod1Categories = product1.getCategories();

        assertThat(prod1Categories).hasSize(2);
        assertThat(prod1Categories).
                extracting("description")
                .contains("CAT1","CAT3");
    }

    @Test
    void testSaveProduct() {
        Product product = new Product();
        product.setDescription("My Product");
        product.setProductStatus(ProductStatus.NEW);

        Product savedProduct = productRepo.save(product);

        Product fetchedProduct = productRepo.findById(savedProduct.getId()).orElse(null);

        assertNotNull(fetchedProduct);
        assertNotNull(fetchedProduct.getDescription());
        assertNotNull(fetchedProduct.getCreatedDate());
        assertNotNull(fetchedProduct.getLastModifiedDate());
    }
}














