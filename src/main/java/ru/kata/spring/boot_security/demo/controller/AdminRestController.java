package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.exception.NoSuchUserException;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {
    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    // Показать всех пользователей
    @GetMapping()
    public ResponseEntity<List<User>> showAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
    // Сохранить пользователя
    @PostMapping()
    public ResponseEntity<User> save(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }
    //Получить пользователя по ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        User user = userService.showUser(id);
        return ResponseEntity.ok(user);
    }
    // Обновить пользователя
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if (user == null) {
            throw new NoSuchUserException("Пользователь не найден");
        }
        userService.edit(user);
        return ResponseEntity.ok(user);
    }
    // Удалить пользователя по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        User user = userService.showUser(id);
        if (user == null) {
            throw new NoSuchUserException("Пользователь с ID " + id + " не найден");
        }
        userService.deleteUserById(id);
        return ResponseEntity.ok("Пользователь с ID: " + id + " был удален.");
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<>(roleService.findAll(),HttpStatus.OK);
    }
}
