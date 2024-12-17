package se.projekt.restaurant_service.service;

import se.projekt.restaurant_service.model.MenuItem;

public class RestaurantMenuItemDTO {
    private Long restaurantId;
    private String restaurantName;
    private String location;
    private String contact;

    private Long menuItemId;
    private String menuItemName;
    private Double price;
    private String description;

    public RestaurantMenuItemDTO(Long restaurantId, String restaurantName, String location, String contact,
                                 Long menuItemId, String menuItemName, Double price, String description) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.location = location;
        this.contact = contact;
        this.menuItemId = menuItemId;
        this.menuItemName = menuItemName;
        this.price = price;
        this.description = description;
    }

    // Getters och Setters
    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
