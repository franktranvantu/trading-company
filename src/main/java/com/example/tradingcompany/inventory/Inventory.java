package com.example.tradingcompany.inventory;

import com.example.tradingcompany.order.OrderDetails;
import com.example.tradingcompany.product.Product;
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
public class Inventory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String address;
  @ManyToMany
  private Set<Product> products = new HashSet<>();
  @OneToMany(mappedBy = "inventory")
  private Set<OrderDetails> orderDetails = new HashSet<>();

  public Inventory(String name, String address, Set<Product> products) {
    this.name = name;
    this.address = address;
    this.products = products;
  }
}
