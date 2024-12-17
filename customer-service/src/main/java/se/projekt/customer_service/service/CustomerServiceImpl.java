package se.projekt.customer_service.service;

import org.springframework.stereotype.Service;
import se.projekt.customer_service.data.AddressRepository;
import se.projekt.customer_service.data.CustomerRepository;
import se.projekt.customer_service.domain.Address;
import se.projekt.customer_service.domain.Customer;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + id));
    }

    @Override
    public List<Address> getAddressByCustomerId(Long customerId) {
        return addressRepository.findByCustomerId(customerId);
    }

    @Override
    public Optional<Address> getAddressByAddressId(Long addressId) {
        return addressRepository.findById(addressId);
    }

    @Override
    public Customer createCustomer(Customer newCustomer, List<Address> addresses) {
        Customer savedCustomer = customerRepository.save(newCustomer);

        if (addresses != null && !addresses.isEmpty()) {
            for (Address address : addresses) {
                address.setCustomerId(savedCustomer.getId());
            }
            addressRepository.saveAll(addresses);
        }
        return savedCustomer;
    }

    @Override
    public Customer updateCustomer(Long id, Customer updateCustomer) {
        Customer existingCustomer = getCustomerById(id);
        existingCustomer.setFirstName(updateCustomer.getFirstName());
        existingCustomer.setLastName(updateCustomer.getLastName());
        existingCustomer.setFullName(updateCustomer.getFirstName(), updateCustomer.getLastName());
        existingCustomer.setEmail(updateCustomer.getEmail());
        existingCustomer.setPhone(updateCustomer.getPhone());
        return customerRepository.save(existingCustomer);
    }

    @Override
    public Address updateAddress(Long addressId, Address updateAddress) {
        return addressRepository.findById(addressId).map(existingAddress -> {
            existingAddress.setStreet(updateAddress.getStreet());
            existingAddress.setCity(updateAddress.getCity());
            existingAddress.setState(updateAddress.getState());
            existingAddress.setZipCode(updateAddress.getZipCode());
            existingAddress.setCustomerId(updateAddress.getCustomerId());
            return addressRepository.save(existingAddress);
        }).orElseThrow(() -> new RuntimeException("Address not found with ID: " + addressId));
    }

    @Override
    public void deleteCustomer(Long customerId) {
        if (customerRepository.existsById(customerId)) {
            List<Address> addresses = addressRepository.findByCustomerId(customerId);
            addressRepository.deleteAll(addresses);
            customerRepository.deleteById(customerId);
        } else {
            throw new RuntimeException("Customer with ID: " + customerId + " not found.");
        }
    }

    @Override
    public void deleteAddress(Long addressId) {
        if (addressRepository.existsById(addressId)) {
            addressRepository.deleteById(addressId);
        } else {
            throw new RuntimeException("Address with ID: " + addressId + " not found.");
        }
    }
}
