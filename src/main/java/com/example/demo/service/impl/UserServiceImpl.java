package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.dal.mysql.UserMapper;
import com.example.demo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import com.example.demo.dal.dataobject.User;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User queryByUsername(String usernameOrOpenId) {
        return userMapper.selectOne(
                new LambdaQueryWrapper<User>()
                        .eq(User::getUsername, usernameOrOpenId));
    }

    @Override
    public Boolean register(User user) {
        int insert = userMapper.insert(user);
        return insert > 0;
    }
}