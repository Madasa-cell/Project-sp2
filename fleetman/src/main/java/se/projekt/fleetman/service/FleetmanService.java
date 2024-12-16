package se.projekt.fleetman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.projekt.fleetman.entity.Delivery;
import se.projekt.fleetman.repository.DeliveryRepository;

@Service
public class FleetmanService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DeliveryRepository deliveryRepository;

    // Metod för att anropa OrderService och hämta orderdetaljer
    public String getOrderDetails(Long orderId) {
        String url = "http://localhost:8082/api/orders/" + orderId; // Byt port om OrderService använder en annan
        return restTemplate.getForObject(url, String.class);
    }

    // Metod för att skapa en ny leverans i DeliveryService
    public void createDelivery(Delivery delivery) {
        deliveryRepository.save(delivery);
    }
}
