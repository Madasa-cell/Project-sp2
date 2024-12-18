package se.projekt.order_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.projekt.order_service.dto.OrderResponseDTO;
import se.projekt.order_service.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Remove @PostMapping since we no longer create orders dynamically
    // We only retrieve the single test order

    @GetMapping("/{id}")
    public OrderResponseDTO getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }
}
