// service/UserService.java
package com.example.SpringTest.service;

import com.example.SpringTest.entity.User;
import com.example.SpringTest.repository.UserRepository;

import java.util.*;

public class UserService {
    private final UserRepository repository = new UserRepository();

    public List<User> getUsers() {
        return repository.getAllUsers();
    }

    public User getUserById(Long id) {
        return repository.getAllUsers().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public User addUser(User user) {
        List<User> users = repository.getAllUsers();
        user.setId(System.currentTimeMillis()); // basic ID generation
        users.add(user);
        repository.saveAllUsers(users);
        return user;
    }

    public User updateUser(Long id, User updatedUser) {
        List<User> users = repository.getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                updatedUser.setId(id);
                users.set(i, updatedUser);
                repository.saveAllUsers(users);
                return updatedUser;
            }
        }
        return null;
    }

    public boolean deleteUser(Long id) {
        List<User> users = repository.getAllUsers();
        boolean removed = users.removeIf(u -> u.getId().equals(id));
        if (removed) repository.saveAllUsers(users);
        return removed;
    }
}
