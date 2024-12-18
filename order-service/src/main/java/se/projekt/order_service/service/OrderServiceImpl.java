package se.projekt.order_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.projekt.order_service.domain.Order;
import se.projekt.order_service.dto.OrderResponseDTO;
import se.projekt.order_service.repository.OrderRepository;
import se.projekt.order_service.util.ExternalServiceClient;

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

    private String customerName;
    private String customerAddress;
    private String restaurantName;

    private Order testOrder;

    @PostConstruct
    public void initTestOrder() {
        try {
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
            Map<String, Object> customer = (Map<String, Object>) customerDetails.get("customer");

            // Fix for addresses list
            List<Map<String, Object>> addresses = (List<Map<String, Object>>) customerDetails.get("addresses");
            Map<String, Object> address = addresses.get(0);

            // Set fields
            this.restaurantName = (String) restaurantDetails.get("restaurantName");
            this.customerName = customer.get("firstName") + " " + customer.get("lastName");
            this.customerAddress = (String) address.get("fullAddress"); // Assuming this key exists

            // Create the test order
            testOrder = new Order();
            testOrder.setCustomerId(customerId);
            testOrder.setRestaurantId(restaurantId);
            testOrder.setItems((String) restaurantDetails.get("menuItemName"));
            testOrder.setTotalPrice(Double.parseDouble(restaurantDetails.get("price").toString()));
            testOrder.setAddress(this.customerAddress); // Include address
            testOrder.setOrderDate(LocalDateTime.now());

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
        dto.setAddress(order.getAddress()); // Include address
        dto.setOrderDate(order.getOrderDate());
        return dto;
    }
}
