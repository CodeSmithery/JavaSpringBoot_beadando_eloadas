package org.example.javaspringboot_beadando_eloadas;

import org.springframework.stereotype.Service;

@Service
public class PaypalPaymentService implements PaymentService {

    @Override
    public void processPayment(double amount) {
        System.out.println("PayPal");
        System.out.println("Amount: " + amount);
    }
}
