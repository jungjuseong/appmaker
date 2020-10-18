package com.clbee.appmaker.service;

import com.clbee.appmaker.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}