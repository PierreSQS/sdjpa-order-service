package guru.springframework.orderservice.repositories;

import guru.springframework.orderservice.domain.OrderHeader;
import guru.springframework.orderservice.domain.OrderLine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderHeaderRepositoryTest {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Test
    void testSaveOrderWithLine() {
        Timestamp createdDate = new Timestamp(61619354100000L);
        System.out.printf("%n####### the created date: %s ########%n",createdDate);
        System.out.printf("####### the created date as long: %d ########%n%n",createdDate.getTime());
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer("New Customer");


        OrderLine orderLine1 = new OrderLine();
        orderLine1.setQuantityOrdered(5);

        OrderLine orderLine2 = new OrderLine();
        orderLine2.setQuantityOrdered(5);
        orderLine2.setCreatedDate(createdDate);

        orderLine1.setOrderHeader(orderHeader);
        orderLine2.setOrderHeader(orderHeader);
        orderHeader.setOrderLines(Set.of(orderLine1,orderLine2));
        OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);

        System.out.printf("%n####### the created date from the DB: %s ########%n%n",
                savedOrder.getCreatedDate());


        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());
        assertNotNull(savedOrder.getOrderLines());
        assertEquals(2, savedOrder.getOrderLines().size());
    }

    @Test
    void testSaveOrder() {
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer("New Customer");
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
}