package com.example.tradingcompany.provider;

import com.example.tradingcompany.dto.ResultDto;
import com.example.tradingcompany.dto.ResultStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/provider")
public class ProviderController {

    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping
    public String index(Model model) {
        List<Provider> providers = providerService.getAllProviders();
        model.addAttribute("providers", providers);
        return "provider-list";
    }

    @GetMapping("/create-provider")
    public String showCreateProvider(@ModelAttribute("provider") Provider provider, Model model) {
        model.addAttribute("action", "Create");
        return "save-provider";
    }

    @PostMapping("/save-provider")
    public String saveProvider(@ModelAttribute("provider") Provider provider, RedirectAttributes ra, Model model) {
        ResultDto result = new ResultDto();
        try {
            if (Objects.isNull(provider.getId())) {
                model.addAttribute("action", "Create");
                providerService.createProvider(provider);
                result.setMessage("Created provider successful!");
            } else {
                model.addAttribute("action", "Update");
                providerService.updateProvider(provider.getId(), provider);
                result.setMessage("Updated provider successful!");
            }
            result.setStatus(ResultStatus.SUCCESS);
            ra.addFlashAttribute("result", result);
            return "redirect:/provider";
        } catch (Exception e) {
            result.setStatus(ResultStatus.FAIL);
            result.setMessage(e.getMessage());
            model.addAttribute("result", result);
            return "save-provider";
        }
    }

    @PostMapping("/update-provider")
    public String showUpdateProvider(@RequestParam long id, Model model) {
        Provider provider = providerService.getProviderById(id);
        model.addAttribute("action", "Update");
        model.addAttribute("provider", provider);
        return "save-provider";
    }

    @PostMapping("/delete-provider")
    public String deleteProvider(@RequestParam long id, RedirectAttributes ra) {
        ResultDto result = new ResultDto();
        try {
            providerService.deleteProvider(id);
            result.setStatus(ResultStatus.SUCCESS);
            result.setMessage("Deleted provider successful!");
        } catch (Exception e) {
            result.setStatus(ResultStatus.FAIL);
            result.setMessage(e.getMessage());
        }
        ra.addFlashAttribute("result", result);
        return "redirect:/provider";
    }
}
