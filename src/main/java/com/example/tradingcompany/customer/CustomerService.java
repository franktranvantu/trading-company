package com.example.tradingcompany.customer;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {

  private final CustomerRepository providerRepository;

  public CustomerService(CustomerRepository providerRepository) {
    this.providerRepository = providerRepository;
  }

  public List<Customer> getAllCustomers() {
    return providerRepository.findAll();
  }

  public Customer getCustomerById(long id) {
    return providerRepository.findById(id).orElse(null);
  }

  public Customer createCustomer(Customer Customer) {
    return providerRepository.save(Customer);
  }

  public Customer updateCustomer(long id, Customer customer) {
    Customer existCustomer = providerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("Customer with id does not exist")));
    if (Objects.nonNull(customer.getName()) && !Objects.equals(existCustomer.getName(), customer.getName())) {
      Optional<Customer> CustomerByName = providerRepository.findCustomerByName(customer.getName());
      if (CustomerByName.isPresent()) {
        throw new IllegalArgumentException("Customer already exists");
      }
      existCustomer.setName(customer.getName());
    }
    return providerRepository.save(existCustomer);
  }

  public void deleteCustomer(long id) {
    Optional<Customer> customer = providerRepository.findById(id);
    if (!customer.isPresent()) {
      throw new IllegalArgumentException(String.format("Customer with id %s not exists", id));
    }
    try {
      providerRepository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new IllegalStateException(String.format("Customer %s is being used by others!", customer.get().getName()));
    }
  }
}
