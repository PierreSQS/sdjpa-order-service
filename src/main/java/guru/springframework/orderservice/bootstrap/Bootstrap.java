package guru.springframework.orderservice.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Modified by Pierrot on 08.03.2025.
 */
@Component
public class Bootstrap implements CommandLineRunner {

    private final BootstrapOrderService bootstrapOrderServ;

    public Bootstrap(BootstrapOrderService bootstrapOrderServ) {
        this.bootstrapOrderServ = bootstrapOrderServ;
    }

    @Override
    public void run(String... args) {
        bootstrapOrderServ.readOrderHeader();
    }
}
