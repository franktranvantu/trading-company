package com.example.tradingcompany.customer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void getAllTest() {
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
        Mockito.when(customerRepository.findAll()).thenReturn(customers);
        Assertions.assertEquals(customerService.getAllCustomers(), customers);
    }

    @Test
    public void getByIdTest() {
        Customer customer = new Customer(1L, "Customer", "Address", "Phone", "Fax", "Email", null);
        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Assertions.assertEquals(customerService.getCustomerById(1L), customer);
    }

    @Test
    public void createTest() {
        Customer customer = new Customer(1L, "Customer", "Address", "Phone", "Fax", "Email", null);
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        Assertions.assertEquals(customerService.createCustomer(customer), customer);
    }

    @Test
    public void updateTest() {
        Customer customer = new Customer(1L, "Customer", "Address", "Phone", "Fax", "Email", null);
        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        Assertions.assertEquals(customerService.updateCustomer(1L, customer), customer);
    }
}
