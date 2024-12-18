package se.projekt.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.projekt.order_service.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
