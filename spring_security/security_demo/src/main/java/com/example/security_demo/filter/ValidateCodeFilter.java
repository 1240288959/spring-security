package com.example.security_demo.filter;

import com.example.security_demo.exception.ValidateCodeFailureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ValidateCodeFilter extends OncePerRequestFilter {

    public static final String SESSION_KEY="SESSION_KEY_IMAGE_CODE";
    private Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String imageCode= (String) httpServletRequest.getSession().getAttribute(SESSION_KEY);
        String requestCode=httpServletRequest.getParameter("validateCode");
        logger.info("imageCode:{}",imageCode);
        logger.info("requestCode:{}",requestCode);
        logger.info(httpServletRequest.getRequestURI());
        if(httpServletRequest.getRequestURI().equals("/user/onlogin")){
            if(!StringUtils.equalsIgnoreCase(imageCode,requestCode)){
                logger.info("验证失败");
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, new ValidateCodeFailureException("验证码错误"));
                return;
            }else{
                //此处应调用验证失败后的handler
                //authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, new AuthenticationException(""));
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
