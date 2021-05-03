package com.example.tradingcompany.category;

import com.example.tradingcompany.dto.ResultDto;
import com.example.tradingcompany.dto.ResultStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String index(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "category-list";
    }

    @GetMapping("/create-category")
    public String showCreateCategory(@ModelAttribute("category") Category category, Model model) {
        model.addAttribute("action", "Create");
        return "save-category";
    }

    @PostMapping("/save-category")
    public String saveCategory(@ModelAttribute("category") Category category, RedirectAttributes ra, Model model) {
        ResultDto result = new ResultDto();
        try {
            if (Objects.isNull(category.getId())) {
                model.addAttribute("action", "Create");
                categoryService.createCategory(category);
                result.setMessage("Created category successful!");
            } else {
                model.addAttribute("action", "Update");
                categoryService.updateCategory(category.getId(), category);
                result.setMessage("Updated category successful!");
            }
            result.setStatus(ResultStatus.SUCCESS);
            ra.addFlashAttribute("result", result);
            return "redirect:/category";
        } catch (Exception e) {
            result.setStatus(ResultStatus.FAIL);
            result.setMessage(e.getMessage());
            model.addAttribute("result", result);
            return "save-category";
        }
    }

    @PostMapping("/update-category")
    public String showUpdateCategory(@RequestParam long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("action", "Update");
        model.addAttribute("category", category);
        return "save-category";
    }

    @PostMapping("/delete-category")
    public String deleteCategory(@RequestParam long id, RedirectAttributes ra) {
        ResultDto result = new ResultDto();
        try {
            categoryService.deleteCategory(id);
            result.setStatus(ResultStatus.SUCCESS);
            result.setMessage("Deleted category successful!");
        } catch (Exception e) {
            result.setStatus(ResultStatus.FAIL);
            result.setMessage(e.getMessage());
        }
        ra.addFlashAttribute("result", result);
        return "redirect:/category";
    }
}
