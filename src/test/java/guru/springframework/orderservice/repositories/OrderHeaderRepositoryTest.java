package guru.springframework.orderservice.repositories;

import guru.springframework.orderservice.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderHeaderRepositoryTest {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    ProductRepository productRepository;

    Product newProduct;

    Customer customer;

    @BeforeEach
    void setUp() {
        Product productToSave = new Product();
        productToSave.setProductStatus(ProductStatus.NEW);
        productToSave.setDescription("Test Product");
        newProduct = productRepository.save(productToSave);

        customer = new Customer();
        customer.setContactInfo("Test Customer");
    }

    @Test
    void testSaveOrderWithLine() {
        LocalDateTime createdDate = LocalDateTime.now();
        System.out.printf("%n####### the date set in the test: %s ########%n",createdDate);

        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer(customer);


        OrderLine orderLine1 = new OrderLine();
        orderLine1.setQuantityOrdered(5);
        orderLine1.setProduct(newProduct);

        OrderLine orderLine2 = new OrderLine();
        orderLine2.setQuantityOrdered(5);
        orderLine2.setCreatedDate(createdDate);

        orderHeader.addOrderLine(orderLine1,orderLine2);
        OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);

        System.out.printf("%n####### the created date from the DB: %s ########%n%n",
                savedOrder.getCreatedDate());

        Long fetchedProdId = orderLine1.getProduct().getId();

        Product fetchedProd = productRepository.findById(fetchedProdId).orElse(null);


        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());
        assertNotNull(savedOrder.getOrderLines());
        assertEquals(2, savedOrder.getOrderLines().size());
        assertThat(Objects.requireNonNull(fetchedProd).getDescription()).isEqualTo("Test Product");
    }

    @Test
    void testSaveOrder() {
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer(customer);
        OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);

        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());

        OrderHeader fetchedOrder = orderHeaderRepository
                .findById(savedOrder.getId()).orElse(null);

        assertNotNull(fetchedOrder);
        assertNotNull(fetchedOrder.getId());
        assertNotNull(fetchedOrder.getCreatedDate());
        assertNotNull(fetchedOrder.getLastModifiedDate());
        assertThat(fetchedOrder.getCustomer().getContactInfo()).isEqualTo("Test Customer");
    }
}