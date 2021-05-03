package com.example.tradingcompany.customer;

import com.example.tradingcompany.dto.ResultDto;
import com.example.tradingcompany.dto.ResultStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String index(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customer-list";
    }

    @GetMapping("/create-customer")
    public String showCreateCustomer(@ModelAttribute("customer") Customer customer, Model model) {
        model.addAttribute("action", "Create");
        return "save-customer";
    }

    @PostMapping("/save-customer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer, RedirectAttributes ra, Model model) {
        ResultDto result = new ResultDto();
        try {
            if (Objects.isNull(customer.getId())) {
                model.addAttribute("action", "Create");
                customerService.createCustomer(customer);
                result.setMessage("Created customer successful!");
            } else {
                model.addAttribute("action", "Update");
                customerService.updateCustomer(customer.getId(), customer);
                result.setMessage("Updated customer successful!");
            }
            result.setStatus(ResultStatus.SUCCESS);
            ra.addFlashAttribute("result", result);
            return "redirect:/customer";
        } catch (Exception e) {
            result.setStatus(ResultStatus.FAIL);
            result.setMessage(e.getMessage());
            model.addAttribute("result", result);
            return "save-customer";
        }
    }

    @PostMapping("/update-customer")
    public String showUpdateCustomer(@RequestParam long id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("action", "Update");
        model.addAttribute("customer", customer);
        return "save-customer";
    }

    @PostMapping("/delete-customer")
    public String deleteCustomer(@RequestParam long id, RedirectAttributes ra) {
        ResultDto result = new ResultDto();
        try {
            customerService.deleteCustomer(id);
            result.setStatus(ResultStatus.SUCCESS);
            result.setMessage("Deleted customer successful!");
        } catch (Exception e) {
            result.setStatus(ResultStatus.FAIL);
            result.setMessage(e.getMessage());
        }
        ra.addFlashAttribute("result", result);
        return "redirect:/customer";
    }
}
