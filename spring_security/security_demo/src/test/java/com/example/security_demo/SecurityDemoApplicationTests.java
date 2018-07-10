package com.example.security_demo;

import com.example.security_demo.mapper.UserMapper;
import com.example.security_demo.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityDemoApplicationTests {

	@Autowired
	private UserMapper userMapper;

	private Logger logger= LoggerFactory.getLogger(getClass());

	@Test
	public void contextLoads() {
	}

	@Test
	public void getUserByUserName(){
		User user=userMapper.getUserByUserName("ty");
		logger.info("user:{}",user);
		System.out.print(user);
		Assert.assertEquals("123",user.getPassword());

	}

}
