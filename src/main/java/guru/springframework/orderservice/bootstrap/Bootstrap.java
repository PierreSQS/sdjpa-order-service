package guru.springframework.orderservice.bootstrap;

import guru.springframework.orderservice.domain.OrderHeader;
import guru.springframework.orderservice.repositories.OrderHeaderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Modified by Pierrot on 06.03.2025.
 */
@Component
public class Bootstrap implements CommandLineRunner {

    private final OrderHeaderRepository orderHeaderRepo;

    public Bootstrap(OrderHeaderRepository orderHeaderRepo) {
        this.orderHeaderRepo = orderHeaderRepo;
    }

    @Transactional
    @Override
    public void run(String... args) {
        OrderHeader orderHeader = orderHeaderRepo.findById(1L).orElse(null);

        System.out.println("### the products in the order ###");
        assert orderHeader != null;
        orderHeader.getOrderLines().forEach(orderLine ->
        {
            System.out.println(orderLine.getProduct().getDescription());

            System.out.println("### the categories of the products ###");
            orderLine.getProduct().getCategories().forEach(category ->
                    System.out.println(category.getDescription()));
        });


    }
}
