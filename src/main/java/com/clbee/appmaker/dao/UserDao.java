package com.clbee.appmaker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.clbee.appmaker.model.User;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}