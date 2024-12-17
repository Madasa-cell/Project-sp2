package se.projekt.customer_service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.projekt.customer_service.domain.Address;
import se.projekt.customer_service.service.CustomerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    private final CustomerService customerService;

    @Autowired
    public AddressController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}/addresses")
    public List<Address> getAddressesByCustomerId(@PathVariable Long customerId) {
        return customerService.getAddressByCustomerId(customerId);
    }

    @GetMapping("/{addressId}/address")
    public Optional<Address> getAddressByAddressId(@PathVariable Long addressId) {
        return customerService.getAddressByAddressId(addressId);
    }

    @PutMapping("/{addressId}/address")
    public Address updateAddress(@PathVariable Long addressId, @RequestBody Address address) {
        return customerService.updateAddress(addressId, address);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Address> deleteAddress(@PathVariable Long id) {
        customerService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}
