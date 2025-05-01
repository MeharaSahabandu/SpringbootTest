// repository/UserRepository.java
package com.example.SpringTest.repository;

import com.example.SpringTest.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.*;

public class UserRepository {
    private final File file = new File("users.json");
    private final ObjectMapper mapper = new ObjectMapper();

    public List<User> getAllUsers() {
        try {
            if (!file.exists()) return new ArrayList<>();
            return mapper.readValue(file, new TypeReference<List<User>>() {});
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void saveAllUsers(List<User> users) {
        try {
            mapper.writeValue(file, users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
