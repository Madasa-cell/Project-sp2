package se.projekt.order_service.util;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalServiceClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public String getCustomerName(Long customerId) {
        String url = "http://localhost:8080/api/customers/" + customerId;
        return restTemplate.getForObject(url, String.class);
    }

    public String getRestaurantName(Long restaurantId) {
        String url = "http://localhost:8081/api/restaurants/" + restaurantId;
        return restTemplate.getForObject(url, String.class);
    }
}
