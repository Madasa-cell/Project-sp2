package se.projekt.order_service.util;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalServiceClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public String getRestaurantDetails(Long restaurantId, Long menuItemId) {
        String url = "http://localhost:8081/restaurants/" + restaurantId + "/" + menuItemId;
        return restTemplate.getForObject(url, String.class);
    }

    public String getCustomerDetails(Long customerId, Long addressId) {
        String url = "http://localhost:8082/customers/" + customerId + "/addresses/" + addressId;
        return restTemplate.getForObject(url, String.class);
    }
}
