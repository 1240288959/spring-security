package com.example.security_demo.mapper;

import com.example.security_demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    @Select("select * from t_user where username = #{username}")
    User getUserByUserName(String username);


}
