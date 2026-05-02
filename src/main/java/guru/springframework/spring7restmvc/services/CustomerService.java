package guru.springframework.spring7restmvc.services;

import guru.springframework.spring7restmvc.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    Customer getCustomerById(UUID uuid);

    List<Customer> getAllCustomers();

    Customer saveNewCustomer(Customer customer);

    void updateById(UUID customerId, Customer customer);

    void deleteById(UUID customerId);

    void patchCustomerById(UUID customerId, Customer customer);
}
