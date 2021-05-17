package com.example.tradingcompany.revenue;

import com.example.tradingcompany.customer.Customer;
import com.example.tradingcompany.customer.CustomerService;
import com.example.tradingcompany.dto.DateRange;
import com.example.tradingcompany.dto.DateTimeRange;
import com.example.tradingcompany.staff.Staff;
import com.example.tradingcompany.staff.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/revenue")
public class RevenueController {

    private final RevenueService revenueService;
    private final CustomerService customerService;
    private final StaffService staffService;

    public RevenueController(RevenueService revenueService,
                             CustomerService customerService,
                             StaffService staffService) {
        this.revenueService = revenueService;
        this.customerService = customerService;
        this.staffService = staffService;
    }

    @GetMapping
    public String index(@RequestParam(required = false) Customer customer,
                        @RequestParam(required = false) Staff staff,
                        @RequestParam(required = false) DateTimeRange date,
                        Model model) {
        model.addAttribute("selectedCustomer", customer);
        model.addAttribute("selectedStaff", staff);
        model.addAttribute("date", date);
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("staffs", staffService.getAllStaffs());
        model.addAttribute("revenues", revenueService.getAllRevenueItems(customer, staff, date));
        return "revenue-list";
    }
}
