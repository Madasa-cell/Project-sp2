package se.projekt.customer_service.service;

import se.projekt.customer_service.domain.Address;
import se.projekt.customer_service.domain.Customer;
import se.projekt.customer_service.rest.CustomerWithAddressResponseDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer getCustomerById(Long id);

    List<Address> getAddressByCustomerId(Long customerId);

    Optional<Address> getAddressByAddressId(Long addressId);

    CustomerWithAddressResponseDTO getCustomerWithAddressIdByCustomerId(Long customerId, Long addressId);

    Customer createCustomer(Customer newCustomer, List<Address> addresses);

    Customer updateCustomer(Long id, Customer updateCustomer);
    Address updateAddress(Long addressId, Address updateAddress);

    void deleteCustomer(Long id);

    void deleteAddress(Long id);
}