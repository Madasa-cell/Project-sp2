package se.projekt.customer_service.rest;

import se.projekt.customer_service.domain.Address;
import se.projekt.customer_service.domain.Customer;

import java.util.List;

public class CustomerWithAddressResponseDTO {
    Customer customer;
    List<Address> addresses;

    public CustomerWithAddressResponseDTO(Customer customer, List<Address> addresses) {
        this.customer = customer;
        this.addresses = addresses;
    }

    public CustomerWithAddressResponseDTO(Customer customer, Address address) {
        this.customer = customer;
        this.addresses = List.of(address);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
