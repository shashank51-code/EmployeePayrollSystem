package com.payroll.controller;

import com.payroll.dto.SignupRequest;
import com.payroll.service.PayrollService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SignupController {
    private final PayrollService payrollService;

    public SignupController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String employeeId,
                         @RequestParam String name,
                         @RequestParam String department,
                         @RequestParam String designation,
                         @RequestParam String password,
                         @RequestParam String confirmPassword,
                         RedirectAttributes redirectAttributes) {
        try {
            payrollService.signup(new SignupRequest(employeeId, name, department, designation, password, confirmPassword));
            return "redirect:/login.html?signup";
        } catch (IllegalArgumentException exception) {
            redirectAttributes.addAttribute("error", exception.getMessage());
            return "redirect:/signup.html";
        }
    }
}
