package se.projekt.fleetman.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.projekt.fleetman.entity.Delivery;
import se.projekt.fleetman.repository.DeliveryRepository;

@Service
public class FleetmanService {

    // Läser URL för OrderService från application.properties
    @Value("${order.service.url}")
    private String orderServiceUrl;

    // Läser URL för DeliveryService från application.properties
    @Value("${delivery.service.url}")
    private String deliveryServiceUrl;

    private final RestTemplate restTemplate;
    private final DeliveryRepository deliveryRepository;

    // Konstruktor för att injicera beroenden
    public FleetmanService(RestTemplate restTemplate, DeliveryRepository deliveryRepository) {
        this.restTemplate = restTemplate;
        this.deliveryRepository = deliveryRepository;
    }

    /**
     * Hämta orderdetaljer från OrderService
     *
     * @param orderId ID för ordern som ska hämtas
     * @return Orderdetaljer som en String
     */
    public String getOrderDetails(Long orderId) {
        String url = orderServiceUrl + "/api/orders/" + orderId;
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * Skapa en ny leverans i DeliveryService
     *
     * @param delivery Leveransobjekt som ska sparas
     */
    public void createDelivery(Delivery delivery) {
        deliveryRepository.save(delivery);
    }
}
