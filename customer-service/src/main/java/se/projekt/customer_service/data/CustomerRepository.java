package se.projekt.customer_service.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.projekt.customer_service.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}