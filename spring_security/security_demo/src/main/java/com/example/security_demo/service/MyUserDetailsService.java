package com.example.security_demo.service;

import com.example.security_demo.mapper.RoleMapper;
import com.example.security_demo.mapper.UserMapper;
import com.example.security_demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userMapper.getUserByUserName(username);
        List<String> roles=roleMapper.findRolenameByUserId(user.getId());
        user.setAuthorities(roles);
        logger.info("自定义User：{}",userMapper.getUserByUserName(username));
        // 数据库中的密码应该为加密后的密码，因为没有用户注册所以没办法手动加密了一下
        String password=passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        logger.info("自定义User：{}",userMapper.getUserByUserName(username));
        return user;
    }
}
