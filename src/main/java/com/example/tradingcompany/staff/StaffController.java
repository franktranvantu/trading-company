package com.example.tradingcompany.staff;

import com.example.tradingcompany.dto.ResultDto;
import com.example.tradingcompany.dto.ResultStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public String index(Model model) {
        List<Staff> staffs = staffService.getAllStaffs();
        model.addAttribute("staffs", staffs);
        return "staff-list";
    }

    @GetMapping("/create-staff")
    public String showCreateStaff(@ModelAttribute("staff") Staff staff, Model model) {
        model.addAttribute("action", "Create");
        return "save-staff";
    }

    @PostMapping("/save-staff")
    public String saveStaff(@ModelAttribute("staff") Staff staff, RedirectAttributes ra, Model model) {
        ResultDto result = new ResultDto();
        try {
            if (Objects.isNull(staff.getId())) {
                model.addAttribute("action", "Create");
                staffService.createStaff(staff);
                result.setMessage("Created staff successful!");
            } else {
                model.addAttribute("action", "Update");
                staffService.updateStaff(staff.getId(), staff);
                result.setMessage("Updated staff successful!");
            }
            result.setStatus(ResultStatus.SUCCESS);
            ra.addFlashAttribute("result", result);
            return "redirect:/staff";
        } catch (Exception e) {
            result.setStatus(ResultStatus.FAIL);
            result.setMessage(e.getMessage());
            model.addAttribute("result", result);
            return "save-staff";
        }
    }

    @PostMapping("/update-staff")
    public String showUpdateStaff(@RequestParam long id, Model model) {
        Staff staff = staffService.getStaffById(id);
        model.addAttribute("action", "Update");
        model.addAttribute("staff", staff);
        return "save-staff";
    }

    @PostMapping("/delete-staff")
    public String deleteStaff(@RequestParam long id, RedirectAttributes ra) {
        ResultDto result = new ResultDto();
        try {
            staffService.deleteStaff(id);
            result.setStatus(ResultStatus.SUCCESS);
            result.setMessage("Deleted staff successful!");
        } catch (Exception e) {
            result.setStatus(ResultStatus.FAIL);
            result.setMessage(e.getMessage());
        }
        ra.addFlashAttribute("result", result);
        return "redirect:/staff";
    }
}
