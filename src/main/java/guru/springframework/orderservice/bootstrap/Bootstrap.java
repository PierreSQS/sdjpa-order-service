package guru.springframework.orderservice.bootstrap;

import guru.springframework.orderservice.domain.Customer;
import guru.springframework.orderservice.repositories.CustomerRepository;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Modified by Pierrot on 08.03.2025.
 */
@Component
public class Bootstrap implements CommandLineRunner {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Bootstrap.class);

    private final BootstrapOrderService bootstrapOrderServ;

    private final CustomerRepository customerRepo;

    public Bootstrap(BootstrapOrderService bootstrapOrderServ, CustomerRepository customerRepo) {
        this.bootstrapOrderServ = bootstrapOrderServ;
        this.customerRepo = customerRepo;
    }

    @Override
    public void run(String... args) {
        bootstrapOrderServ.readOrderHeader();

        Customer customer = new Customer();
        customer.setCustomerName("Pierrot");
        Customer savedCustomer = customerRepo.save(customer);

        // log the saved customer version
        log.info("### The Customer Version: {} ###", savedCustomer.getVersion());

        // delete the saved customer
        customerRepo.delete(savedCustomer);

    }
}
