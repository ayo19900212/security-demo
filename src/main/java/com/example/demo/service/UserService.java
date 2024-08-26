package com.example.demo.service;


import com.example.demo.dal.dataobject.User;

public interface UserService {

    User queryByUsername(String username);

    /**
     * 注册用户
     * @param user
     * @return
     */
    Boolean register(User user);
}
