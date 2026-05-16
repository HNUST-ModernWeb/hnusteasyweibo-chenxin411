package com.example.socialbackend1.service;

import com.example.socialbackend1.entity.User;
import com.example.socialbackend1.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user == null) return null;
        if (!user.getPassword().equals(password)) return null;
        return user;
    }

    public int register(User user) {
        return userMapper.addUser(user);
    }

    // 新增：更新用户信息
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }
}