package com.example.tradingcompany.revenue;

import com.example.tradingcompany.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevenueItem {

  private Product product;
  private Integer buyQuantity;
  private Integer saleQuantity;
  private Double balance;

}
