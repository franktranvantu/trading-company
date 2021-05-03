package com.example.tradingcompany.order;

import com.example.tradingcompany.customer.CustomerService;
import com.example.tradingcompany.dto.ResultDto;
import com.example.tradingcompany.dto.ResultStatus;
import com.example.tradingcompany.product.ProductService;
import com.example.tradingcompany.staff.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;
    private final ProductService productService;
    private final StaffService staffService;
    private final CustomerService customerService;

    public OrderDetailsController(OrderDetailsService orderDetailsService, ProductService productService, StaffService staffService, CustomerService customerService) {
        this.orderDetailsService = orderDetailsService;
        this.productService = productService;
        this.staffService = staffService;
        this.customerService = customerService;
    }

    @GetMapping
    public String index(Model model) {
        List<OrderDetails> orders = orderDetailsService.getAllOrderDetails();
        model.addAttribute("orders", orders);
        return "order-list";
    }

    @GetMapping("/create-order")
    public String showCreateOrderDetails(@ModelAttribute("order") OrderDetails orderDetails, Model model) {
        model.addAttribute("action", "Create");
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("staffs", staffService.getAllStaffs());
        model.addAttribute("customers", customerService.getAllCustomers());
        return "save-order";
    }

    @PostMapping("/save-order")
    public String createOrderDetails(@ModelAttribute("order") OrderDetails orderDetails, RedirectAttributes ra, Model model) {
        ResultDto result = new ResultDto();
        try {
            model.addAttribute("action", "Create");
            orderDetailsService.createOrderDetails(orderDetails);
            result.setMessage("Created order successful!");
            result.setStatus(ResultStatus.SUCCESS);
            ra.addFlashAttribute("result", result);
            return "redirect:/order";
        } catch (Exception e) {
            result.setStatus(ResultStatus.FAIL);
            result.setMessage(e.getMessage());
            model.addAttribute("result", result);
            return "save-order";
        }
    }

    @PostMapping("/update-order")
    public String showUpdateOrder(@RequestParam long id, Model model) {
        OrderDetails order = orderDetailsService.getOrderDetailsById(id);
        model.addAttribute("action", "Update");
        model.addAttribute("order", order);
        model.addAttribute("staffs", staffService.getAllStaffs());
        model.addAttribute("customers", customerService.getAllCustomers());
        return "save-order";
    }

    @PostMapping("/delete-order")
    public String deleteOrderDetails(@RequestParam long id, RedirectAttributes ra) {
        ResultDto result = new ResultDto();
        try {
            orderDetailsService.deleteOrderDetails(id);
            result.setStatus(ResultStatus.SUCCESS);
            result.setMessage("Deleted order successful!");
        } catch (Exception e) {
            result.setStatus(ResultStatus.FAIL);
            result.setMessage(e.getMessage());
        }
        ra.addFlashAttribute("result", result);
        return "redirect:/order";
    }
}
