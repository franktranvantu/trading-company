package com.example.tradingcompany.inventory;

import com.example.tradingcompany.product.Product;
import com.example.tradingcompany.staff.Staff;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryReceivingNote {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDate date;
  @OneToOne(mappedBy = "receivingNote")
  private Staff staff;
  @ManyToMany(mappedBy = "receivingNotes")
  private Set<Product> products = new HashSet<>();
  private Integer quantity;

}
