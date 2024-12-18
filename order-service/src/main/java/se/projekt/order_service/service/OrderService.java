package se.projekt.order_service.service;

import se.projekt.order_service.dto.OrderResponseDTO;

public interface OrderService {
    // Remove the createOrder method
    OrderResponseDTO getOrderById(Long orderId);
}
