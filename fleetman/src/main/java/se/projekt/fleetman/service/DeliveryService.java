package se.projekt.fleetman.service;

import se.projekt.fleetman.entity.Delivery;
import se.projekt.fleetman.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public Delivery createDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    public Delivery updateDeliveryStatus(Long deliveryId, String status) {
        Optional<Delivery> deliveryOptional = deliveryRepository.findById(deliveryId);
        if (deliveryOptional.isEmpty()) {
            throw new RuntimeException("Delivery not found with ID: " + deliveryId);
        }
        Delivery delivery = deliveryOptional.get();
        delivery.setStatus(status);
        return deliveryRepository.save(delivery);
    }

    public List<Delivery> getDeliveriesByOrderId(Long orderId) {
        return deliveryRepository.findByOrderId(orderId);
    }

    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }
}
