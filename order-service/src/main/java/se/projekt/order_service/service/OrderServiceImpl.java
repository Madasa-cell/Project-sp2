package se.projekt.order_service.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.projekt.order_service.domain.Order;
import se.projekt.order_service.dto.OrderResponseDTO;
import se.projekt.order_service.repository.OrderRepository;
import se.projekt.order_service.util.ExternalServiceClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ExternalServiceClient externalServiceClient;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Order testOrder;
    private String customerName;
    private String restaurantName;

    @PostConstruct
    public void initTestOrder() {
        try {
            // Hardcoded IDs for the test order
            Long customerId = 2L;
            Long restaurantId = 1L;
            Long menuItemId = 3L;
            Long addressId = 3L;

            // Fetch restaurant details
            String restaurantDetailsJson = externalServiceClient.getRestaurantDetails(restaurantId, menuItemId);
            Map<String, Object> restaurantDetails = objectMapper.readValue(restaurantDetailsJson, Map.class);

            // Fetch customer details
            String customerDetailsJson = externalServiceClient.getCustomerDetails(customerId, addressId);
            Map<String, Object> customerDetails = objectMapper.readValue(customerDetailsJson, Map.class);

            // Extract customer and address information
            Map<String, Object> customer = (Map<String, Object>) customerDetails.get("customer");
            List<Map<String, Object>> addresses = (List<Map<String, Object>>) customerDetails.get("addresses");
            Map<String, Object> address = addresses.get(0);

            // Set restaurant and customer names
            this.restaurantName = (String) restaurantDetails.get("restaurantName");
            this.customerName = customer.get("firstName") + " " + customer.get("lastName");

            // Create the test order
            testOrder = new Order();
            testOrder.setCustomerId(customerId);
            testOrder.setRestaurantId(restaurantId);
            testOrder.setItems((String) restaurantDetails.get("menuItemName"));
            testOrder.setTotalPrice(Double.parseDouble(restaurantDetails.get("price").toString()));
            testOrder.setOrderDate(LocalDateTime.now());

            // Save the test order in the database
            testOrder = orderRepository.save(testOrder);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize test order", e);
        }
    }

    @Override
    public OrderResponseDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return mapToResponseDTO(order);
    }

    private OrderResponseDTO mapToResponseDTO(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setCustomerId(order.getCustomerId());
        dto.setRestaurantId(order.getRestaurantId());
        dto.setCustomerName(customerName);
        dto.setRestaurantName(restaurantName);
        dto.setItems(order.getItems());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setOrderDate(order.getOrderDate());
        return dto;
    }
}
