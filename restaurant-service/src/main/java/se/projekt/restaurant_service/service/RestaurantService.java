package se.projekt.restaurant_service.service;

import org.springframework.stereotype.Service;
import se.projekt.restaurant_service.model.Restaurant;
import se.projekt.restaurant_service.repository.RestaurantRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    // Hämta alla restauranger
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    // Lägg till en ny restaurang
    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    // Hämta en specifik restaurang baserat på ID
    public Optional<Restaurant> getRestaurantById(Long id) {
        return restaurantRepository.findById(id);
    }

    // Spara en restaurang (används vid uppdateringar)
    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    // Uppdatera en befintlig restaurang
    public Restaurant updateRestaurant(Long id, Restaurant updatedRestaurant) {
        return restaurantRepository.findById(id).map(existingRestaurant -> {
            existingRestaurant.setName(updatedRestaurant.getName());
            existingRestaurant.setLocation(updatedRestaurant.getLocation());
            existingRestaurant.setContact(updatedRestaurant.getContact());
            return restaurantRepository.save(existingRestaurant);
        }).orElseThrow(() -> new IllegalArgumentException("Restaurant with ID " + id + " not found"));
    }

    // Ta bort en restaurang
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }
}
