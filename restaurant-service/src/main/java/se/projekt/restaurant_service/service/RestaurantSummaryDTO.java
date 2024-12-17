package se.projekt.restaurant_service.service;

public class RestaurantSummaryDTO {
    private Long id;
    private String name;
    private String location;
    private String contact;
    private int menuItemCount; // Antal menyartiklar

    // Constructor
    public RestaurantSummaryDTO(Long id, String name, String location, String contact, int menuItemCount) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.contact = contact;
        this.menuItemCount = menuItemCount;
    }

    // Getters och Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getMenuItemCount() {
        return menuItemCount;
    }

    public void setMenuItemCount(int menuItemCount) {
        this.menuItemCount = menuItemCount;
    }
}
