package com.example.tradingcompany.staff;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Order(5)
public class StaffBootStrap implements CommandLineRunner {

  private final StaffRepository staffRepository;

  public StaffBootStrap(StaffRepository staffRepository) {
    this.staffRepository = staffRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    List<Staff> staffs = Stream.iterate(1L, i -> i + 1)
        .map(i -> new Staff(
            String.format("Staff %d", i),
            String.format("Address %d", i),
            String.format("Phone %d", i),
            String.format("staff%d@gmail.com", i)
        ))
        .limit(50)
        .collect(Collectors.toList());

    staffRepository.saveAll(staffs);
  }
}
