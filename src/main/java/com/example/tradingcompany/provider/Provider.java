package com.example.tradingcompany.provider;

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
public class Provider {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String address;
  private String phone;
  private String fax;
  private String email;
  @OneToMany(mappedBy = "provider")
  private Set<Product> products = new HashSet<>();
  @OneToMany(mappedBy = "provider")
  private Set<OrderDetails> orderDetails = new HashSet<>();

  public Provider(String name, String address, String phone, String fax, String email) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.fax = fax;
    this.email = email;
  }
}
