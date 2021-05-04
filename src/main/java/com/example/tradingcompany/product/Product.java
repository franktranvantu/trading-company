package com.example.tradingcompany.product;

import com.example.tradingcompany.category.Category;
import com.example.tradingcompany.inventory.Inventory;
import com.example.tradingcompany.order.OrderDetails;
import com.example.tradingcompany.provider.Provider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String model;
  private String brand;
  @ManyToOne
  @JoinTable(
      name = "product_provider",
      joinColumns = {@JoinColumn(name = "product_id")},
      inverseJoinColumns = {@JoinColumn}
  )
  private Provider provider;
  private String description;
  @ManyToMany(mappedBy = "products")
  private Set<OrderDetails> orderDetails = new HashSet<>();
  @ManyToOne
  private Category category;
  private Double buyingPrice;
  private Double sellingPrice;
  @ManyToMany(mappedBy = "products")
  private Set<Inventory> inventories = new HashSet<>();

  public Product(String name,
                 String model,
                 String brand,
                 Provider provider,
                 String description,
                 Category category,
                 Double buyingPrice,
                 Double sellingPrice) {
    this.name = name;
    this.model = model;
    this.brand = brand;
    this.provider = provider;
    this.description = description;
    this.category = category;
    this.buyingPrice = buyingPrice;
    this.sellingPrice = sellingPrice;
  }
}
