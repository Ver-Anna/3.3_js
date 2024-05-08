package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    User showUser(Long id);

    void save(User user);

    void deleteUserById(Long id);

    void edit(Long id, User user);

    Optional<User> findByUsername(String username);
}
