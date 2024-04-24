package com.uniplan.controller;

import com.uniplan.controller.main.Attributes;
import com.uniplan.model.Users;
import com.uniplan.model.perechisl.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/userAdd")
public class UserAddCont extends Attributes {

    @GetMapping
    public String AddUser(Model model) {
        AddAttributesAddUser(model);
        return "userAdd";
    }

    @PostMapping("/add")
    public String AddNewUser(Model model, @RequestParam String username, @RequestParam String password, @RequestParam String fio, @RequestParam Role role) {
        if (usersRepo.findByUsername(username) != null) {
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            AddAttributesAddUser(model);
            return "userAdd";
        }
        usersRepo.save(new Users(username, password, fio, role, defaultAvatar));
        return "redirect:/userAdd";
    }
}
