package com.example.security_demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Component
@Mapper
public interface RoleMapper {

    @Select("select rolename from t_user,t_role,t_user_role where t_user.id=t_user_role.user and t_role.id=t_user_role.role and t_user.id = #{id}")
    List<String> findRolenameByUserId(int id);
}
