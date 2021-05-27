package com.example.tradingcompany.customer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void findAllTest() {
        Assertions.assertEquals(customerRepository.findAll().size(), 50);
    }

    @Test
    public void findByIdTest() {
        Customer customerById = customerRepository.findAll().stream()
                .filter(category -> category.getId() == 1)
                .findFirst()
                .orElse(null);
        Customer category = customerRepository.findById(1L).orElse(null);
        Assertions.assertEquals(customerById, category);
    }

    @Test
    @Rollback(false)
    public void saveTest() {
        Customer customer = new Customer("Customer", "Address", "Phone", "Fax", "Email");
        Customer savedCustomer = customerRepository.save(customer);
        Assertions.assertEquals(customer, savedCustomer);
    }
}
