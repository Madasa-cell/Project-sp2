package se.projekt.restaurant_service.service;

import org.springframework.stereotype.Service;
import se.projekt.restaurant_service.model.MenuItem;
import se.projekt.restaurant_service.repository.MenuItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    // Uppdatera en menyartikel
    public MenuItem updateMenuItem(Long id, MenuItem updatedMenuItem) {
        return menuItemRepository.findById(id)
                .map(existingItem -> {
                    existingItem.setName(updatedMenuItem.getName());
                    existingItem.setPrice(updatedMenuItem.getPrice());
                    existingItem.setDescription(updatedMenuItem.getDescription());
                    existingItem.setRestaurant(updatedMenuItem.getRestaurant());
                    return menuItemRepository.save(existingItem);
                })
                .orElseThrow(() -> new IllegalArgumentException("MenuItem med ID " + id + " hittades inte"));
    }

    // Hämta alla menyartiklar
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    // Lägg till en ny menyartikel
    public MenuItem addMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }
}
