package com.example.tradingcompany.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Order(2)
public class CustomerBootStrap implements CommandLineRunner {

  private final CustomerRepository providerRepository;

  public CustomerBootStrap(CustomerRepository providerRepository) {
    this.providerRepository = providerRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    List<Customer> customers = Stream.iterate(1L, i -> i + 1)
        .map(i -> new Customer(
            String.format("Customer %d", i),
            String.format("Address %d", i),
            String.format("Phone %d", i),
            String.format("Fax %d", i),
            String.format("customer%d@gmail.com", i)
        ))
        .limit(50)
        .collect(Collectors.toList());

    providerRepository.saveAll(customers);
  }
}
