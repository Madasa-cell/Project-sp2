package se.projekt.restaurant_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.projekt.restaurant_service.service.RestaurantSummaryDTO;
import se.projekt.restaurant_service.service.RestaurantMenuItemDTO;
import se.projekt.restaurant_service.model.MenuItem;
import se.projekt.restaurant_service.model.Restaurant;
import se.projekt.restaurant_service.service.RestaurantService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    // Hämta alla restauranger med antal menyartiklar
    @GetMapping
    public ResponseEntity<List<RestaurantSummaryDTO>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();

        // Konvertera till RestaurantSummaryDTO
        List<RestaurantSummaryDTO> summaries = restaurants.stream()
                .map(r -> new RestaurantSummaryDTO(
                        r.getId(),
                        r.getName(),
                        r.getLocation(),
                        r.getContact(),
                        r.getMenuItems().size() // Antal menyartiklar
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(summaries);
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

    // Hämta en specifik menyartikel för en viss restaurang
    @GetMapping("/{restaurantId}/{menuItemId}")
    public ResponseEntity<RestaurantMenuItemDTO> getRestaurantAndMenuItem(
            @PathVariable Long restaurantId, @PathVariable Long menuItemId) {

        Optional<Restaurant> restaurantOpt = restaurantService.getRestaurantById(restaurantId);

        if (restaurantOpt.isPresent()) {
            Restaurant restaurant = restaurantOpt.get();

            // Hitta menyartikeln
            Optional<MenuItem> menuItemOpt = restaurant.getMenuItems().stream()
                    .filter(menuItem -> menuItem.getId().equals(menuItemId))
                    .findFirst();

            if (menuItemOpt.isPresent()) {
                MenuItem menuItem = menuItemOpt.get();

                // Skapa DTO med all nödvändig information
                RestaurantMenuItemDTO response = new RestaurantMenuItemDTO(
                        restaurant.getId(),
                        restaurant.getName(),
                        restaurant.getLocation(),
                        restaurant.getContact(),
                        menuItem.getId(),
                        menuItem.getName(),
                        menuItem.getPrice(),
                        menuItem.getDescription()
                );

                return ResponseEntity.ok(response);
            }
        }

        return ResponseEntity.notFound().build();
    }
}
