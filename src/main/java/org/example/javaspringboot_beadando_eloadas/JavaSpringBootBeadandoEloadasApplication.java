package org.example.javaspringboot_beadando_eloadas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JavaSpringBootBeadandoEloadasApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(JavaSpringBootBeadandoEloadasApplication.class, args);
        var orderService = context.getBean(OrderService.class);
//        var orderService = new OrderService(new PaypalPaymentService());
//        orderService.setPaymentService(new PaypalPaymentService());
        orderService.placeOrder();
    }

}