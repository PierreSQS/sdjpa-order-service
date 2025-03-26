package guru.springframework.orderservice.bootstrap;

import guru.springframework.orderservice.domain.OrderHeader;
import guru.springframework.orderservice.repositories.OrderHeaderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Modified by Pierrot on 26-03-2025.
 */
@Service
public class BootstrapOrderService {
    // add slf4j logger
    private static final Logger logger = LoggerFactory.getLogger(BootstrapOrderService.class);
    
    private final OrderHeaderRepository orderHeaderRepository;

    public BootstrapOrderService(OrderHeaderRepository orderHeaderRepository) {
        this.orderHeaderRepository = orderHeaderRepository;
    }

    @Transactional
    public void readOrderData(){
        OrderHeader orderHeader = orderHeaderRepository.findById(55L).orElseThrow(EntityNotFoundException::new);

        orderHeader.getOrderLines().forEach(ol -> {
            logger.info(ol.getProduct().getDescription());

            ol.getProduct().getCategories().forEach(cat -> logger.info(cat.getDescription()));
        });
    }

}
