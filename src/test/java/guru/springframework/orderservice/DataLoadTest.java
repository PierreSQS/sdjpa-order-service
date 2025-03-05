package guru.springframework.orderservice;

import guru.springframework.orderservice.domain.*;
import guru.springframework.orderservice.repositories.CustomerRepository;
import guru.springframework.orderservice.repositories.OrderHeaderRepository;
import guru.springframework.orderservice.repositories.ProductRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Modified by Pierrot on 05-03-2025.
 */
@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DataLoadTest {
    final String product1 = "Product 1";
    final String product2 = "Product 2";
    final String product3 = "Product 3";

    final String testCustomer = "TEST CUSTOMER";

    @Autowired
    OrderHeaderRepository orderHeaderRepo;

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    ProductRepository productRepo;

    @Test
    void testN_PlusOneProblem() {
        Customer customer = customerRepo.
                findCustomerByCustomerNameIgnoreCase(testCustomer).orElse(null);

        IntSummaryStatistics totalOrdered = orderHeaderRepo.findAllByCustomer(customer)
                .stream()
                .flatMap(orderHeader -> orderHeader.getOrderLines().stream())
                .collect(Collectors.summarizingInt(OrderLine::getQuantityOrdered));

        System.out.printf("%n####### total ordered: %s #######%n%n", totalOrdered.getSum());

        assertThat(totalOrdered.getSum()).isGreaterThan(0);

    }

    @Test
    void testLazyVsEager() {
        OrderHeader orderHeader = orderHeaderRepo.findById(5L).orElse(null);
        assert orderHeader != null;
        System.out.printf("%n##### Order ID: %d%n",orderHeader.getId());
        System.out.printf("##### Customer Name: %s%n%n",orderHeader.getCustomer().getCustomerName());
        assertThat(orderHeader.getId()).isNotNull();
    }

    @Disabled("Disabled for convenience reasons, feel free to enable it if needed!!!")
    @Rollback(value = false)
    @Test
    void testDataLoader() {
        List<Product> products = loadProducts();
        Customer customer = loadCustomers();

        int ordersToCreate = 15;

        for (int i = 0; i < ordersToCreate; i++){
            System.out.println("Creating order #: " + i);
            saveOrder(customer, products);
        }

        orderHeaderRepo.flush();

        assertThat(orderHeaderRepo.count()).isEqualTo(ordersToCreate);
    }

    private OrderHeader saveOrder(Customer customer, List<Product> products){
        Random random = new Random();

        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer(customer);

        products.forEach(product -> {
            OrderLine orderLine = new OrderLine();
            orderLine.setProduct(product);
            orderLine.setQuantityOrdered(random.nextInt(20));
            orderHeader.addOrderLines(orderLine);
        });

        return orderHeaderRepo.save(orderHeader);
    }

    private Customer loadCustomers() {
        return getOrSaveCustomer(testCustomer);
    }

    private Customer getOrSaveCustomer(String customerName) {
        return customerRepo.findCustomerByCustomerNameIgnoreCase(customerName)
                .orElseGet(() -> {
                    Customer c1 = new Customer();
                    c1.setCustomerName(customerName);
                    c1.setEmail("test@example.com");
                    Address address = new Address();
                    address.setAddress("123 Main");
                    address.setCity("New Orleans");
                    address.setState("LA");
                    c1.setAddress(address);
                    return customerRepo.save(c1);
                });
    }
    private List<Product> loadProducts(){
        List<Product> products = new ArrayList<>();

        products.add(getOrSaveProduct(product1));
        products.add(getOrSaveProduct(product2));
        products.add(getOrSaveProduct(product3));

        return products;
    }
    private Product getOrSaveProduct(String description) {
        return productRepo.findByDescription(description)
                .orElseGet(() -> {
                    Product p1 = new Product();
                    p1.setDescription(description);
                    p1.setProductStatus(ProductStatus.NEW);
                    return productRepo.save(p1);
                });
    }

}
