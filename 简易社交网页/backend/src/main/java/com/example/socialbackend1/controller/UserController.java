package com.example.socialbackend1.controller;

import com.example.socialbackend1.entity.User;
import com.example.socialbackend1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserMapper userMapper;

    // 登录
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        User u = userMapper.findByUsername(user.getUsername());
        if (u == null || !u.getPassword().equals(user.getPassword())) {
            map.put("code", 500);
            map.put("msg", "账号或密码错误");
        } else {
            map.put("code", 200);
            map.put("data", u);
        }
        return map;
    }

    // 注册
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        User exist = userMapper.findByUsername(user.getUsername());
        if (exist != null) {
            map.put("code", 400);
            map.put("msg", "用户名已存在");
            return map;
        }
        userMapper.addUser(user);
        map.put("code", 200);
        map.put("msg", "注册成功");
        return map;
    }

    // 修改资料（头像、邮箱、用户名都能改！且不会重复报错！）
    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();

        User oldUser = userMapper.findById(user.getId());
        if (oldUser == null) {
            map.put("code", 500);
            map.put("msg", "用户不存在");
            return map;
        }

        // 只有当用户名发生变化时，才检查重复
        if (!oldUser.getUsername().equals(user.getUsername())) {
            User existUser = userMapper.findByUsername(user.getUsername());
            if (existUser != null) {
                map.put("code", 400);
                map.put("msg", "用户名已存在");
                return map;
            }
        }

        // 更新所有信息
        oldUser.setUsername(user.getUsername());
        oldUser.setEmail(user.getEmail());
        oldUser.setAvatar(user.getAvatar());

        userMapper.updateUser(oldUser);

        map.put("code", 200);
        map.put("msg", "修改成功");
        map.put("data", oldUser);
        return map;
    }
}