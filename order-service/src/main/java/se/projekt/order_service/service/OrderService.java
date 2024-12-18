package se.projekt.order_service.service;

import se.projekt.order_service.dto.OrderRequestDTO;
import se.projekt.order_service.dto.OrderResponseDTO;

public interface OrderService {
    OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO);
    OrderResponseDTO getOrderById(Long orderId);
}
