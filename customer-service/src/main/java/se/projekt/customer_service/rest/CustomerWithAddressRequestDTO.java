package se.projekt.customer_service.rest;

import se.projekt.customer_service.domain.Address;
import se.projekt.customer_service.domain.Customer;

import java.util.List;

public class CustomerWithAddressRequestDTO {
    Customer customer;
    List<Address> addresses;

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
