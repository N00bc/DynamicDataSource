package com.cyn.dynamic;

import com.cyn.dynamic.entity.User;
import com.cyn.dynamic.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DynamicDataSourceApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    private UserService userService;

    @Test
    void testDynamic() {
        List<User> allUsers = userService.getAllUsers();
        allUsers.forEach(System.out::println);
    }
}
