package se.projekt.order_service.service;

import se.projekt.order_service.model.Order;
import java.util.List;

public interface OrderService {

    List<Order> getOrders();

    Order getOrderById(Long id);
}
