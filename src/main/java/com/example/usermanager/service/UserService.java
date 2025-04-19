package com.example.usermanager.service;

import com.example.usermanager.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> list();

    Optional<User> find(Long id);

    User create(User user);

    User update(Long id, User user);

    boolean delete(Long id);

}