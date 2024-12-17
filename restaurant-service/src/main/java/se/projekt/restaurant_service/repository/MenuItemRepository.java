package se.projekt.restaurant_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.projekt.restaurant_service.model.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
