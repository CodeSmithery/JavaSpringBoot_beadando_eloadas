package org.example.javaspringboot_beadando_eloadas;

public class PaypalPaymentService implements PaymentService {

    @Override
    public void processPayment(double amount) {
        System.out.println("PayPal");
        System.out.println("Amount: " + amount);
    }
}
