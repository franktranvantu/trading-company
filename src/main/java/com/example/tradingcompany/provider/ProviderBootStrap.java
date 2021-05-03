package com.example.tradingcompany.provider;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Order(1)
public class ProviderBootStrap implements CommandLineRunner {

  private final ProviderRepository providerRepository;

  public ProviderBootStrap(ProviderRepository providerRepository) {
    this.providerRepository = providerRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    List<Provider> providers = Stream.iterate(1L, i -> i + 1)
        .map(i -> new Provider(
            String.format("Provider %d", i),
            String.format("Address %d", i),
            String.format("Phone %d", i),
            String.format("Fax %d", i),
            String.format("provider%d@gmail.com", i)
        ))
        .limit(15)
        .collect(Collectors.toList());

    providerRepository.saveAll(providers);
  }
}
