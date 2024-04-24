package com.uniplan.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeCont {

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }
}
