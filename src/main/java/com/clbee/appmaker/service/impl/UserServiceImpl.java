package com.clbee.appmaker.service.impl;

import com.clbee.appmaker.dao.RoleDao;
import com.clbee.appmaker.dao.UserDao;
import com.clbee.appmaker.model.User;
import com.clbee.appmaker.security.SHAPasswordEncoder;
import com.clbee.appmaker.service.UserService;
import com.clbee.appmaker.util.ShaPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private SHAPasswordEncoder encoder;

    @Override
    public void save(User user) {

        //user.setPassword(ShaPassword.changeSHA256(user.getPassword()));
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleDao.findAll()));
        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}