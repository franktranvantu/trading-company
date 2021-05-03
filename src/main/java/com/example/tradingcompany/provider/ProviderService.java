package com.example.tradingcompany.provider;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProviderService {

  private final ProviderRepository providerRepository;

  public ProviderService(ProviderRepository providerRepository) {
    this.providerRepository = providerRepository;
  }

  public List<Provider> getAllProviders() {
    return providerRepository.findAll();
  }

  public Provider getProviderById(long id) {
    return providerRepository.findById(id).orElse(null);
  }

  public Provider createProvider(Provider provider) {
    return providerRepository.save(provider);
  }

  public Provider updateProvider(long id, Provider provider) {
    Provider existProvider = providerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("Provider with id does not exist")));
    if (Objects.nonNull(provider.getName()) && !Objects.equals(existProvider.getName(), provider.getName())) {
      Optional<Provider> providerByName = providerRepository.findProviderByName(provider.getName());
      if (providerByName.isPresent()) {
        throw new IllegalArgumentException("Provider already exists");
      }
      existProvider.setName(provider.getName());
    }
    return providerRepository.save(existProvider);
  }

  public void deleteProvider(long id) {
    Optional<Provider> provider = providerRepository.findById(id);
    if (!provider.isPresent()) {
      throw new IllegalArgumentException(String.format("Provider with id %s not exists", id));
    }
    try {
      providerRepository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new IllegalStateException(String.format("Provider %s is being used by others!", provider.get().getName()));
    }
  }
}
