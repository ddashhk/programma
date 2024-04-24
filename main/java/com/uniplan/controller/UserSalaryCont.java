package com.uniplan.controller;

import com.uniplan.controller.main.Attributes;
import com.uniplan.model.Users;
import com.uniplan.model.perechisl.Salary;
import com.uniplan.model.perechisl.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/userSalary")
public class UserSalaryCont extends Attributes {

    @GetMapping
    public String userSalary(Model model) {
        AddAttributes(model);
        model.addAttribute("users", usersRepo.findAllByRole(Role.EMPLOYEE));
        model.addAttribute("salaries", Salary.values());
        return "userSalary";
    }

    @PostMapping("/{id}/edit")
    public String userSalaryEdit(@PathVariable long id, @RequestParam Salary salary) {
        Users user = usersRepo.getReferenceById(id);
        user.setSalary(salary);
        usersRepo.save(user);
        return "redirect:/userSalary";
    }
}
