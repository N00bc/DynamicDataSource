package com.cyn.dynamic.service;

import com.cyn.dynamic.anno.DynamicDataSource;
import com.cyn.dynamic.entity.User;
import com.cyn.dynamic.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Godc
 * @description:
 * @date 2023/6/20/0020 0:33
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @DynamicDataSource(dataSource = "slave")
    public List<User> getAllUsers() {
        List<User> users = userMapper.listAllUsers();
        System.out.println("执行完成Service代码");
        return users;
    }
}
