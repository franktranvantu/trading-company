package com.example.tradingcompany.inventory;

import com.example.tradingcompany.dto.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Objects;
import java.util.Set;

public class InventorySpecification implements Specification<Inventory> {

  private SearchCriteria criteria;

  public InventorySpecification(SearchCriteria criteria) {
    this.criteria = criteria;
  }

  @Override
  public Predicate toPredicate(Root<Inventory> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    if (Objects.isNull(criteria.getValue())) {
      return null;
    }
    if (root.get(criteria.getKey()).getJavaType() == String.class) {
      return builder.like(builder.lower(root.get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase() + "%");
    } else if (root.get(criteria.getKey()).getJavaType() == Set.class) {
      query.distinct(true);
      return builder.like(builder.lower(root.join(criteria.getKey(), JoinType.INNER).get("product").get("name")), "%" + criteria.getValue().toString().toLowerCase() + "%");
    }
    return null;
  }
}
