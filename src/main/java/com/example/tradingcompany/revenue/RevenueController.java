package com.example.tradingcompany.revenue;

import com.example.tradingcompany.dto.DateRange;
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

    public RevenueController(RevenueService revenueService) {
        this.revenueService = revenueService;
    }

    @GetMapping
    public String index(@RequestParam(required = false) String category,
                        @RequestParam(required = false) DateRange date,
                        Model model) {
        List<RevenueItem> revenues = revenueService.getAllRevenueItems();
        model.addAttribute("revenues", revenues);
        return "revenue-list";
    }
}
