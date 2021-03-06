package com.backend.service;

import com.backend.model.User;
import com.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }
    public Optional<User> findById(Integer id) { return  userRepository.findById(id); }
    public List<User> findAll() {
        return userRepository.findAll(); }

    public void remove(Integer id) {
        userRepository.deleteById(id);
    }
}
