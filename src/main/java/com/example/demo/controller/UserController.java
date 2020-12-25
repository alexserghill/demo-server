package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("users")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("users/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping("users")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("users/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("users/{id}")
    public int delete(@PathVariable Long id) {
        return userService.delete(id);
    }

    @PostMapping("create")
    public boolean create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("login")
    public boolean login(@RequestBody User user) {
        return userService.login(user);
    }

    @GetMapping("exists/{username}")
    public boolean exists(@PathVariable String username) {
        return userService.existsByUsername(username);
    }

}
