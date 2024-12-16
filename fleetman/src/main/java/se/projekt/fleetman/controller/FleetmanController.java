package se.projekt.fleetman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.projekt.fleetman.entity.Delivery;
import se.projekt.fleetman.service.FleetmanService;

@RestController
@RequestMapping("/api/fleetman")
public class FleetmanController {

    @Autowired
    private FleetmanService fleetmanService;

    // Endpoint: Hämta orderdetaljer via OrderService
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<String> getOrderDetails(@PathVariable Long orderId) {
        String orderDetails = fleetmanService.getOrderDetails(orderId);
        return ResponseEntity.ok(orderDetails);
    }

    // Endpoint: Skapa en ny leverans i DeliveryService
    @PostMapping("/deliveries")
    public ResponseEntity<String> createDelivery(@RequestBody Delivery delivery) {
        fleetmanService.createDelivery(delivery);
        return ResponseEntity.ok("Delivery created successfully");
    }
}
