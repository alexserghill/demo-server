package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(Long id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    public int delete(Long id) {
        return userRepository.delete(id);
    }

    public boolean create(User user) {
        return save(user) != null;
    }

    public boolean login(User user) {
        return userRepository.findByUsernameAndPassword(
                user.getUsername(), user.getPassword()) != null;
    }

    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username) != null;
    }

}
