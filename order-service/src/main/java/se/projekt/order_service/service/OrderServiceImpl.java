package se.projekt.order_service.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import se.projekt.order_service.domain.Order;
import se.projekt.order_service.dto.OrderRequestDTO;
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

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        // Hämta data från externa tjänster
        String customerName = externalServiceClient.getCustomerName(orderRequestDTO.getCustomerId());
        String restaurantName = externalServiceClient.getRestaurantName(orderRequestDTO.getRestaurantId());

        // Skapa order
        Order order = new Order();
        order.setCustomerId(orderRequestDTO.getCustomerId());
        order.setRestaurantId(orderRequestDTO.getRestaurantId());
        order.setItems(orderRequestDTO.getItems());
        order.setTotalPrice(calculateTotalPrice(orderRequestDTO.getItems()));
        order.setOrderDate(LocalDateTime.now());

        // Spara i databasen
        Order savedOrder = orderRepository.save(order);

        // Returnera som DTO
        return mapToResponseDTO(savedOrder, customerName, restaurantName);
    }

    @Override
    public OrderResponseDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        String customerName = externalServiceClient.getCustomerName(order.getCustomerId());
        String restaurantName = externalServiceClient.getRestaurantName(order.getRestaurantId());
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

    private double calculateTotalPrice(String items) {
        // Enkel placeholder-logik
        return 100.0;
    }
}
