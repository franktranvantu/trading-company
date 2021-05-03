package com.example.tradingcompany.product;

import com.example.tradingcompany.category.CategoryService;
import com.example.tradingcompany.dto.ResultDto;
import com.example.tradingcompany.dto.ResultStatus;
import com.example.tradingcompany.provider.ProviderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProviderService providerService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, ProviderService providerService, CategoryService categoryService) {
        this.productService = productService;
        this.providerService = providerService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String index(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/create-product")
    public String showCreateProduct(@ModelAttribute("product") Product product, Model model) {
        model.addAttribute("action", "Create");
        model.addAttribute("providers", providerService.getAllProviders());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "save-product";
    }

    @PostMapping("/save-product")
    public String saveProduct(@ModelAttribute("product") Product product, RedirectAttributes ra, Model model) {
        ResultDto result = new ResultDto();
        try {
            if (Objects.isNull(product.getId())) {
                model.addAttribute("action", "Create");
                productService.createProduct(product);
                result.setMessage("Created product successful!");
            } else {
                model.addAttribute("action", "Update");
                productService.updateProduct(product.getId(), product);
                result.setMessage("Updated product successful!");
            }
            result.setStatus(ResultStatus.SUCCESS);
            ra.addFlashAttribute("result", result);
            return "redirect:/product";
        } catch (Exception e) {
            result.setStatus(ResultStatus.FAIL);
            result.setMessage(e.getMessage());
            model.addAttribute("result", result);
            return "save-product";
        }
    }

    @PostMapping("/update-product")
    public String showUpdateProduct(@RequestParam long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("action", "Update");
        model.addAttribute("product", product);
        model.addAttribute("providers", providerService.getAllProviders());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "save-product";
    }

    @PostMapping("/delete-product")
    public String deleteProduct(@RequestParam long id, RedirectAttributes ra) {
        ResultDto result = new ResultDto();
        try {
            productService.deleteProduct(id);
            result.setStatus(ResultStatus.SUCCESS);
            result.setMessage("Deleted product successful!");
        } catch (Exception e) {
            result.setStatus(ResultStatus.FAIL);
            result.setMessage(e.getMessage());
        }
        ra.addFlashAttribute("result", result);
        return "redirect:/product";
    }
}
