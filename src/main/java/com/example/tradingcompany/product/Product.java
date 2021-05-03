package com.example.tradingcompany.product;

import com.example.tradingcompany.category.Category;
import com.example.tradingcompany.inventory.InventoryDeliveryNote;
import com.example.tradingcompany.inventory.InventoryReceivingNote;
import com.example.tradingcompany.order.OrderDetails;
import com.example.tradingcompany.provider.Provider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
      joinColumns = {@JoinColumn},
      inverseJoinColumns = {@JoinColumn}
  )
  private Provider provider;
  private String description;
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "product_order_details",
      joinColumns = {@JoinColumn},
      inverseJoinColumns = {@JoinColumn}
  )
  private Set<OrderDetails> orderDetails = new HashSet<>();
  @ManyToOne
  @JoinTable(
      name = "product_category",
      joinColumns = {@JoinColumn},
      inverseJoinColumns = {@JoinColumn}
  )
  private Category category;
  private Double buyingPrice;
  private Double sellingPrice;
  @ManyToMany
  @JoinTable(
      name = "product_delivery_notes",
      joinColumns = {@JoinColumn},
      inverseJoinColumns = {@JoinColumn}
  )
  private Set<InventoryDeliveryNote> deliveryNotes = new HashSet<>();
  @ManyToMany
  @JoinTable(
      name = "product_receiving_notes",
      joinColumns = {@JoinColumn},
      inverseJoinColumns = {@JoinColumn}
  )
  private Set<InventoryReceivingNote> receivingNotes = new HashSet<>();

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
