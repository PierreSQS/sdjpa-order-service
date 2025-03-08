package guru.springframework.orderservice.bootstrap;

import guru.springframework.orderservice.domain.OrderHeader;
import guru.springframework.orderservice.repositories.OrderHeaderRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Pierrot on 08-03-2025.
 */
@Service
public class BootstrapOrderService {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(BootstrapOrderService.class);

    private final OrderHeaderRepository orderHeaderRepo;

    public BootstrapOrderService(OrderHeaderRepository orderHeaderRepo) {
        this.orderHeaderRepo = orderHeaderRepo;
    }

    /**
     * External call. The @Transaction annotation is considered!!!.
     * See call in the Bootstrap class.
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


}
