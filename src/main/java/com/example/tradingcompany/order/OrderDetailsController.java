package com.example.tradingcompany.order;

import com.example.tradingcompany.customer.Customer;
import com.example.tradingcompany.customer.CustomerService;
import com.example.tradingcompany.dto.DateTimeRange;
import com.example.tradingcompany.dto.ResultDto;
import com.example.tradingcompany.dto.ResultStatus;
import com.example.tradingcompany.inventory.Inventory;
import com.example.tradingcompany.inventory.InventoryService;
import com.example.tradingcompany.product.Product;
import com.example.tradingcompany.product.ProductService;
import com.example.tradingcompany.provider.Provider;
import com.example.tradingcompany.provider.ProviderService;
import com.example.tradingcompany.staff.Staff;
import com.example.tradingcompany.staff.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/order")
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;
    private final ProductService productService;
    private final StaffService staffService;
    private final CustomerService customerService;
    private final ProviderService providerService;
    private final InventoryService inventoryService;

    public OrderDetailsController(OrderDetailsService orderDetailsService,
                                  ProductService productService,
                                  StaffService staffService,
                                  CustomerService customerService,
                                  ProviderService providerService,
                                  InventoryService inventoryService) {
        this.orderDetailsService = orderDetailsService;
        this.productService = productService;
        this.staffService = staffService;
        this.customerService = customerService;
        this.providerService = providerService;
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public String index(@RequestParam(required = false) Staff staff,
                        @RequestParam(required = false) Product product,
                        @RequestParam(required = false) Provider provider,
                        @RequestParam(required = false) Customer customer,
                        @RequestParam(required = false) Inventory inventory,
                        @RequestParam(required = false) DateTimeRange date,
                        Model model) {
        List<OrderDetails> orders = orderDetailsService.getAllOrderDetails(staff, product, provider, customer, inventory, date);
        model.addAttribute("staffs", staffService.getAllStaffs());
        model.addAttribute("selectedStaff", staff);
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("selectedProduct", product);
        model.addAttribute("providers", providerService.getAllProviders());
        model.addAttribute("selectedProvider", provider);
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("selectedCustomer", customer);
        model.addAttribute("inventories", inventoryService.getAllInventories());
        model.addAttribute("selectedInventory", inventory);
        model.addAttribute("date", date);
        model.addAttribute("orders", orders);
        return "order-list";
    }

    @GetMapping("/create-order")
    public String showCreateOrderDetails(@ModelAttribute("order") OrderDetails orderDetails, Model model) {
        model.addAttribute("action", "Create");
        model.addAttribute("staffs", staffService.getAllStaffs());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("providers", providerService.getAllProviders());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("inventories", inventoryService.getAllInventories());
        return "save-order";
    }

    @PostMapping("/save-order")
    public String createOrderDetails(@ModelAttribute("order") OrderDetails orderDetails,
                                     RedirectAttributes ra,
                                     Model model) {
        ResultDto result = new ResultDto();
        try {
            model.addAttribute("action", Objects.isNull(orderDetails.getId()) ? "Create" : "Update");
            orderDetailsService.createOrderDetails(orderDetails);
            result.setMessage("Created order successful!");
            result.setStatus(ResultStatus.SUCCESS);
            ra.addFlashAttribute("result", result);
            return "redirect:/order";
        } catch (Exception e) {
            result.setStatus(ResultStatus.FAIL);
            result.setMessage(e.getMessage());
            model.addAttribute("result", result);
            model.addAttribute("staffs", staffService.getAllStaffs());
            model.addAttribute("customers", customerService.getAllCustomers());
            model.addAttribute("providers", providerService.getAllProviders());
            model.addAttribute("products", productService.getAllProducts());
            model.addAttribute("inventories", inventoryService.getAllInventories());
            return "save-order";
        }
    }

    @PostMapping("/update-order")
    public String showUpdateOrder(@RequestParam long id, Model model) {
        OrderDetails order = orderDetailsService.getOrderDetailsById(id);
        model.addAttribute("action", "Update");
        model.addAttribute("staffs", staffService.getAllStaffs());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("providers", providerService.getAllProviders());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("inventories", inventoryService.getAllInventories());
        model.addAttribute("order", order);
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
