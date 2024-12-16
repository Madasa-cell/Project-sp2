package se.projekt.fleetman.repository;

import se.projekt.fleetman.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    // Hämta alla leveranser för en specifik order
    List<Delivery> findByOrderId(Long orderId);
}