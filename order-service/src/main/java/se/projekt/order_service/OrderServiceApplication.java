package se.projekt.order_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
		// TODO:
		// Create test order.
		// Using information from CustomerService and ResturantService.
		// We need to make api-call in the java code.
		// And storing it in a new Order-object.
		// Finally storing the order-object in our order database.

		// We also need an "endpoint".
}
