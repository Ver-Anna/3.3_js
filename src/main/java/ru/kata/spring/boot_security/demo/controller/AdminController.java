package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users/admin")
public class AdminController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    //показать всех пользователей из БД
    @GetMapping()
    public String showAllUsers(Model model) {
        model.addAttribute("admin", userService.getAllUsers());
        model.addAttribute("roles", roleService.findAll());
        return "admin";
    }

    //редактирование пользователя
    @GetMapping("/{id}/editUser")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.showUser(id));
        model.addAttribute("roles", roleService.findAll());
        return "editUser";
    }

    //обновление пользователя
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) { //есть ли ошибки в данном объекте
            return "editUser"; //возвращаем ту форму с ошибками
        }

        userService.edit(id, user);
        return "redirect:/users/admin";
    }

    //удаление пользователя
    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/users/admin";
    }

    //показывыть данные пользователя
    @GetMapping(value = "/{id}/showUser")
    public String showUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.showUser(id));
        return "showUser";
    }

    //создание нового пользователя
    @GetMapping(value = "/newUser")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.findAll());
        return "newUser";
    }

    // Сохранение нового пользователя
    @PostMapping()
    public String save(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { //есть ли ошибки в данном объекте
            return "/newUser"; //возвращаем ту форму с ошибками
        }
        userService.save(user);
        return "redirect:/users/admin";
    }
}
