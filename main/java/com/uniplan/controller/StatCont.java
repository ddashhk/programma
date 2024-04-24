package com.uniplan.controller;

import com.uniplan.controller.main.Attributes;
import com.uniplan.model.Users;
import com.uniplan.model.perechisl.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/stat")
public class StatCont extends Attributes {

    @GetMapping
    public String Stat(Model model) {
        AddAttributes(model);
        List<Users> users = usersRepo.findAllByRole(Role.EMPLOYEE);

        Collections.reverse(users);

        int[] integers = new int[5];
        String[] strings = new String[5];


        int[] intKPD = new int[5];
        String[] stringsKPD = new String[5];

        Collections.reverse(users);

        for (int i = 0; i < users.size(); i++) {
            if (i == 5) break;
            stringsKPD[i] = users.get(i).getFio();
        }


        model.addAttribute("integers", integers);
        model.addAttribute("strings", strings);
        model.addAttribute("intKPD", intKPD);
        model.addAttribute("stringsKPD", stringsKPD);
        model.addAttribute("employees", users);
        return "stat";
    }
}
