package guru.springframework.orderservice.repositories;

import guru.springframework.orderservice.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderHeaderRepositoryTest {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    Product newProduct;

    @BeforeEach
    void setUp() {
        Product productToSave = new Product();
        productToSave.setProductStatus(ProductStatus.NEW);
        productToSave.setDescription("TEST PRODUCT");
        this.newProduct = productRepository.saveAndFlush(productToSave);
    }

    @Test
    void testSaveOrderWithLine() {
        LocalDateTime createdDate = LocalDateTime.now();
        System.out.printf("%n####### the date set in the test: %s ########%n",createdDate);

        OrderHeader orderHeader = new OrderHeader();
        Customer customer = new Customer();
        customer.setCustomerName("New Customer");
        Customer savedCustomer = customerRepository.save(customer);

        orderHeader.setCustomer(savedCustomer);

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setQuantityOrdered(5);
        orderLine1.setProduct(newProduct);

        OrderLine orderLine2 = new OrderLine();
        orderLine2.setQuantityOrdered(3);
        orderLine2.setCreatedDate(createdDate);
        orderLine2.setProduct(newProduct);

        orderHeader.addOrderLines(orderLine1,orderLine2);
        OrderApproval orderApproval = new OrderApproval();
        orderApproval.setApprovedBy("Pierrot");

        orderHeader.setOrderApproval(orderApproval);

        OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);
        orderHeaderRepository.flush();

        System.out.printf("%n####### the created date from the DB: %s ########%n%n",
                savedOrder.getCreatedDate());

        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());
        assertNotNull(savedOrder.getOrderLines());
        assertEquals(2, savedOrder.getOrderLines().size());

        // additional assertion to practice on assertJ.
        Set<OrderLine> orderLines = savedOrder.getOrderLines();
        assertThat(orderLines)
                .extracting(orderLine -> orderLine.getProduct().getDescription())
                .contains("TEST PRODUCT");

        OrderHeader fetchedOrder = orderHeaderRepository.findById(savedOrder.getId()).orElse(null);

        assertThat(fetchedOrder.getOrderApproval())
                .extracting("approvedBy")
                .isEqualTo("Pierrot");

        assertNotNull(fetchedOrder);
        assertEquals(2, fetchedOrder.getOrderLines().size());
    }

    @Test
    void testSaveOrder() {
        OrderHeader orderHeader = new OrderHeader();
        Customer customer = new Customer();
        customer.setCustomerName("New Customer");
        Customer savedCustomer = customerRepository.save(customer);

        orderHeader.setCustomer(savedCustomer);
        OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);

        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());

        OrderHeader fetchedOrder = orderHeaderRepository
                .findById(savedOrder.getId()).orElse(null);

        assertNotNull(fetchedOrder);
        assertNotNull(fetchedOrder.getId());
        assertNotNull(fetchedOrder.getCreatedDate());
        assertNotNull(fetchedOrder.getLastModifiedDate());
    }

    @Test
    void testDeleteCascade() {
        OrderHeader orderHeader = new OrderHeader();
        Customer customer = new Customer();
        customer.setCustomerName("New Customer");
        Customer savedCustomer = customerRepository.save(customer);

        orderHeader.setCustomer(savedCustomer);

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setQuantityOrdered(5);
        orderLine1.setProduct(newProduct);

        OrderLine orderLine2 = new OrderLine();
        orderLine2.setQuantityOrdered(3);
        orderLine2.setProduct(newProduct);

        orderHeader.addOrderLines(orderLine1,orderLine2);
        OrderApproval orderApproval = new OrderApproval();
        orderApproval.setApprovedBy("Pierrot");

        orderHeader.setOrderApproval(orderApproval);

        OrderHeader savedOrder = orderHeaderRepository.saveAndFlush(orderHeader);
        System.out.println("###### order saved and flushed ######");

        orderHeaderRepository.deleteById(savedOrder.getId());
        orderHeaderRepository.flush();



    }
}