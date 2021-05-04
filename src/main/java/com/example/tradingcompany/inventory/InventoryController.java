package com.example.tradingcompany.inventory;

import com.example.tradingcompany.dto.ResultDto;
import com.example.tradingcompany.dto.ResultStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public String index(@RequestParam(required = false) String name,
                        @RequestParam(required = false) String address,
                        @RequestParam(required = false) String product,
                        Model model) {
        List<Inventory> inventories = inventoryService.getAllInventories(name, address, product);
        model.addAttribute("inventories", inventories);
        return "inventory-list";
    }

    @GetMapping("/create-inventory")
    public String showCreateInventory(@ModelAttribute("inventory") Inventory inventory, Model model) {
        model.addAttribute("action", "Create");
        return "save-inventory";
    }

    @PostMapping("/save-inventory")
    public String saveInventory(@ModelAttribute("inventory") Inventory inventory, RedirectAttributes ra, Model model) {
        ResultDto result = new ResultDto();
        try {
            if (Objects.isNull(inventory.getId())) {
                model.addAttribute("action", "Create");
                inventoryService.createInventory(inventory);
                result.setMessage("Created inventory successful!");
            } else {
                model.addAttribute("action", "Update");
                inventoryService.updateInventory(inventory.getId(), inventory);
                result.setMessage("Updated inventory successful!");
            }
            result.setStatus(ResultStatus.SUCCESS);
            ra.addFlashAttribute("result", result);
            return "redirect:/inventory";
        } catch (Exception e) {
            result.setStatus(ResultStatus.FAIL);
            result.setMessage(e.getMessage());
            model.addAttribute("result", result);
            return "save-inventory";
        }
    }

    @PostMapping("/update-inventory")
    public String showUpdateInventory(@RequestParam long id, Model model) {
        Inventory inventory = inventoryService.getInventoryById(id);
        model.addAttribute("action", "Update");
        model.addAttribute("inventory", inventory);
        return "save-inventory";
    }

    @PostMapping("/delete-inventory")
    public String deleteInventory(@RequestParam long id, RedirectAttributes ra) {
        ResultDto result = new ResultDto();
        try {
            inventoryService.deleteInventory(id);
            result.setStatus(ResultStatus.SUCCESS);
            result.setMessage("Deleted inventory successful!");
        } catch (Exception e) {
            result.setStatus(ResultStatus.FAIL);
            result.setMessage(e.getMessage());
        }
        ra.addFlashAttribute("result", result);
        return "redirect:/inventory";
    }
}
