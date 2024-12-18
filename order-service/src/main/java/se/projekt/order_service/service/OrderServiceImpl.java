package se.projekt.order_service.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import se.projekt.order_service.domain.Order;
import se.projekt.order_service.dto.OrderResponseDTO;
import se.projekt.order_service.repository.OrderRepository;
import se.projekt.order_service.util.ExternalServiceClient;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ExternalServiceClient externalServiceClient;

    private Order testOrder;
    private String customerName;
    private String restaurantName;

    @PostConstruct
    public void initTestOrder() {
        // Hardcode the IDs for the test order
        Long customerId = 2L;
        Long restaurantId = 1L;
        String items = "TestItem1,TestItem2";
        double totalPrice = 100.0;

        // Call external endpoints (hardcoded) to get names
        this.customerName = externalServiceClient.getCustomerName(customerId);
        this.restaurantName = externalServiceClient.getRestaurantName(restaurantId);

        // Create and save the test order
        testOrder = new Order();
        testOrder.setCustomerId(customerId);
        testOrder.setRestaurantId(restaurantId);
        testOrder.setItems(items);
        testOrder.setTotalPrice(totalPrice);
        testOrder.setOrderDate(LocalDateTime.now());

        testOrder = orderRepository.save(testOrder);
    }

    @Override
    public OrderResponseDTO getOrderById(Long orderId) {
        // Since we only have one test order, and we know its ID is 1, we can just return it.
        // But to follow the pattern, we actually fetch from DB:
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return mapToResponseDTO(order, customerName, restaurantName);
    }

    private OrderResponseDTO mapToResponseDTO(Order order, String customerName, String restaurantName) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setCustomerId(order.getCustomerId());
        dto.setRestaurantId(order.getRestaurantId());
        dto.setCustomerName(customerName);
        dto.setRestaurantName(restaurantName);
        dto.setItems(order.getItems());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setOrderDate(order.getOrderDate());
        return dto;
    }

}
