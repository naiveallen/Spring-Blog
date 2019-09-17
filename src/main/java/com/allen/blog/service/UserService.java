package com.allen.blog.service;

import com.allen.blog.bean.User;
import com.allen.blog.dao.UserRepository;
import com.allen.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User checkUser(String username, String password) {
        password = MD5Utils.code(password);
        User user = userRepository.findByUsernameAndPassword(username, password);
        return user;
    }

}
