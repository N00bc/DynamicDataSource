package com.cyn.dynamic.mapper;

import com.cyn.dynamic.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Godc
 * @description:
 * @date 2023/6/20/0020 0:32
 */
@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> listAllUsers();
}
