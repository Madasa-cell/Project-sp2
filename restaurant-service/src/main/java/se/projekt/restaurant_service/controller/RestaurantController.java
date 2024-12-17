package se.projekt.restaurant_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.projekt.restaurant_service.model.MenuItem;
import se.projekt.restaurant_service.model.Restaurant;
import se.projekt.restaurant_service.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    // Hämta alla restauranger
    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(restaurants);
    }

    // Lägg till en ny restaurang
    @PostMapping
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant createdRestaurant = restaurantService.addRestaurant(restaurant);
        return ResponseEntity.ok(createdRestaurant);
    }

    // Lägg till menyartiklar till en specifik restaurang
    @PostMapping("/{id}/menu")
    public ResponseEntity<List<MenuItem>> addMenuItemsToRestaurant(
            @PathVariable Long id, @RequestBody List<MenuItem> menuItems) {
        return restaurantService.getRestaurantById(id).map(restaurant -> {
            menuItems.forEach(item -> item.setRestaurant(restaurant));
            restaurant.getMenuItems().addAll(menuItems);
            restaurantService.saveRestaurant(restaurant);
            return ResponseEntity.ok(restaurant.getMenuItems());
        }).orElse(ResponseEntity.notFound().build());
    }

    // Hämta en specifik restaurang
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
