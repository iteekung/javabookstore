package io.spring.bookstore.service;

import io.spring.bookstore.domain.User;
public interface UserService {

    User getUser(String username);
    User saveUser(User user);
    void deleteUser(User user);
}