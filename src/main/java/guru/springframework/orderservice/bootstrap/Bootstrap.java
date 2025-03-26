package guru.springframework.orderservice.bootstrap;

import guru.springframework.orderservice.domain.Customer;
import guru.springframework.orderservice.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Modified by Pierrot on 26-03-2025.
 */
@Component
public class Bootstrap implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);
    public static final String VERSION_IS = "### Version is: ";
    public static final String VERSION_MSG = "{} ###";

    private final BootstrapOrderService bootstrapOrderService;

    private final CustomerRepository customerRepository;

    public Bootstrap(BootstrapOrderService bootstrapOrderService, CustomerRepository customerRepository) {
        this.bootstrapOrderService = bootstrapOrderService;
        this.customerRepository = customerRepository;
    }


    @Override
    public void run(String... args) {
        bootstrapOrderService.readOrderData();

        Customer customer = new Customer();
        customer.setCustomerName("Testing Version");
        Customer savedCustomer = customerRepository.save(customer);
        logger.info(VERSION_IS + VERSION_MSG, savedCustomer.getVersion());

        savedCustomer.setCustomerName("Testing Version 2");
        Customer savedCustomer2 = customerRepository.save(savedCustomer);
        logger.info(VERSION_IS + VERSION_MSG, savedCustomer2.getVersion());

        savedCustomer2.setCustomerName("Testing Version 3");
        Customer savedCustomer3 = customerRepository.save(savedCustomer2);
        logger.info(VERSION_IS + VERSION_MSG, savedCustomer3.getVersion());

        customerRepository.delete(savedCustomer3);
    }
}






