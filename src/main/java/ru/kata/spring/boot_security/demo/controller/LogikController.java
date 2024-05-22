package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogikController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }
    @GetMapping("/user")
    public String userPage() {
        return "user";
    }

//    @GetMapping("/main")
//    public ModelAndView getMainPage() {
//        return new ModelAndView("main");
//    }

}
