package guru.springframework.orderservice.bootstrap;

import guru.springframework.orderservice.domain.OrderHeader;
import guru.springframework.orderservice.repositories.OrderHeaderRepository;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Modified by Pierrot on 08.03.2025.
 */
@Component
public class Bootstrap implements CommandLineRunner {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Bootstrap.class);
    
    private final OrderHeaderRepository orderHeaderRepo;

    public Bootstrap(OrderHeaderRepository orderHeaderRepo) {
        this.orderHeaderRepo = orderHeaderRepo;
    }

    /**
     * Internal call. The @Transaction annotation is ignored!!!.
     */
    @Transactional
    public void readOrderHeader() {
        OrderHeader orderHeader = orderHeaderRepo.findById(1L).orElse(null);

        log.info("### the products in the order ###");
        assert orderHeader != null;
        orderHeader.getOrderLines().forEach(orderLine ->
        {
            log.info("### {} ###",orderLine.getProduct().getDescription());

            log.info("### the categories of the product ###");
            orderLine.getProduct().getCategories().forEach(category ->
                    log.info(category.getDescription()));
        });
    }

    @Override
    public void run(String... args) {

        readOrderHeader();

    }
}
