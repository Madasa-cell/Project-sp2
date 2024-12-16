package se.projekt.fleetman.service;

import org.springframework.stereotype.Service;
import se.projekt.fleetman.entity.Delivery;
import se.projekt.fleetman.repository.DeliveryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    public Delivery createDelivery(Delivery delivery) {
        if (delivery.getDeliveryTime() == null) {
            delivery.setDeliveryTime(java.time.LocalDateTime.now());
        }
        return deliveryRepository.save(delivery);
    }

    // Uppdatera leveransstatus
    public Optional<Delivery> updateDeliveryStatus(Long id, String newStatus) {
        return deliveryRepository.findById(id).map(delivery -> {
            delivery.setStatus(newStatus); // Uppdaterar status
            return deliveryRepository.save(delivery); // Sparar uppdaterad leverans
        });
    }
}
