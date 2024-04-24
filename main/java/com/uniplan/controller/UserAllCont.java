package com.uniplan.controller;

import com.uniplan.controller.main.Attributes;
import com.uniplan.model.Users;
import com.uniplan.model.perechisl.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/userAll")
public class UserAllCont extends Attributes {

    @GetMapping
    public String Profiles(Model model) {
        AddAttributesProfiles(model);
        return "userAll";
    }

    @PostMapping("/{id}/edit")
    public String ProfilesEdit(@PathVariable long id, @RequestParam Role role) {
        Users user = usersRepo.getReferenceById(id);
        user.setRole(role);
        usersRepo.save(user);
        return "redirect:/userAll";
    }

    @GetMapping("/{id}/delete")
    public String ProfilesDelete(Model model, @PathVariable long id) {
        if (getUser().getId() == id) {
            AddAttributesProfiles(model);
            model.addAttribute("message", "Вы не можете удалить свой профиль");
            return "userAll";
        }

        usersRepo.deleteById(id);

        return "redirect:/userAll";
    }
}
