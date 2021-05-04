package com.example.tradingcompany.product;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Product getProductById(long id) {
    return productRepository.findById(id).orElse(null);
  }

  public List<Product> getProductsLikeName(String name) {
    return productRepository.findProductsLikeName(name);
  }

  public Product createProduct(Product product) {
    return productRepository.save(product);
  }

  public Product updateProduct(long id, Product product) {
    Product existProduct = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("Product with id does not exist")));
    if (Objects.nonNull(product.getName()) && !Objects.equals(existProduct.getName(), product.getName())) {
      Optional<Product> productByName = productRepository.findProductByName(product.getName());
      if (productByName.isPresent()) {
        throw new IllegalArgumentException("Product already exists");
      }
      existProduct.setName(product.getName());
    }
    return productRepository.save(existProduct);
  }

  public void deleteProduct(long id) {
    Optional<Product> product = productRepository.findById(id);
    if (!product.isPresent()) {
      throw new IllegalArgumentException(String.format("Product with id %s not exists", id));
    }
    try {
      productRepository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new IllegalStateException(String.format("Product %s is being used by others!", product.get().getName()));
    }
  }
}
