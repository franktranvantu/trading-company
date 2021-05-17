package com.example.tradingcompany.order;

import com.example.tradingcompany.customer.Customer;
import com.example.tradingcompany.dto.DateTimeRange;
import com.example.tradingcompany.dto.SearchCriteria;
import com.example.tradingcompany.staff.Staff;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.Objects;

public class RevenueOrderDetailsSpecification implements Specification<OrderDetails> {

    private SearchCriteria criteria;

    public RevenueOrderDetailsSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<OrderDetails> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (Objects.isNull(criteria.getValue())) {
            return null;
        }
        if (root.get(criteria.getKey()).getJavaType() == Customer.class) {
            Customer customer = (Customer) criteria.getValue();
            return builder.equal(root.get(criteria.getKey()), customer);
        } else if (root.get(criteria.getKey()).getJavaType() == Staff.class) {
            Staff staff = (Staff) criteria.getValue();
            return builder.equal(root.get(criteria.getKey()), staff);
        } else if (root.get(criteria.getKey()).getJavaType() == LocalDateTime.class) {
            DateTimeRange dateTimeRange = (DateTimeRange) criteria.getValue();
            return builder.between(root.get(criteria.getKey()), dateTimeRange.getFrom(), dateTimeRange.getTo());
        }
        return null;
    }
}
