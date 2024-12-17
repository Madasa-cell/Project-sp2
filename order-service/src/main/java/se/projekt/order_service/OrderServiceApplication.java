package com.example.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);

        // Create test order here
        // This test order will GET information from restaurantService
        // and CustomerService and save it as an Order-Object to the
        // to the order database.

    }
}
