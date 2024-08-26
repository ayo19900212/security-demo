package com.example.demo.dal.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.dal.dataobject.User;

public interface UserMapper extends BaseMapper<User> {

    User queryByUsername(String username);
}
