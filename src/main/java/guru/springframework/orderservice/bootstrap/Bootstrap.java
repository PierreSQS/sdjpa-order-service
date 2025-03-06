package guru.springframework.orderservice.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Modified by Pierrot on 06.03.2025.
 */
@Component
public class Bootstrap implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("##### Hello from Bootstrap #####");
    }
}
