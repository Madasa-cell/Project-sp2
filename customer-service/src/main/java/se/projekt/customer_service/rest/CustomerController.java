package se.projekt.customer_service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import se.projekt.customer_service.domain.Customer;
import se.projekt.customer_service.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{customerId}/addresses/{addressId}")
    public ResponseEntity<CustomerWithAddressResponseDTO> getCustomerIdWithAddressIdByCustomerId(
            @PathVariable Long customerId, @PathVariable Long addressId
    ) {
        return ResponseEntity.ok(customerService.getCustomerWithAddressIdByCustomerId(customerId, addressId));
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerWithAddressRequestDTO request) {
        return ResponseEntity.ok(customerService.createCustomer(request.getCustomer(), request.getAddresses()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}