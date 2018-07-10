package com.example.security_demo.controller;

import com.example.security_demo.model.ImageCode;
import org.apache.catalina.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
public class ValidateCodeController {
    public static final String SESSION_KEY="SESSION_KEY_IMAGE_CODE";

    @RequestMapping("/code/image")
    @ResponseBody
    public String createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode=ImageCode.generate(new ServletWebRequest(request));
        HttpSession session=request.getSession();
        session.setAttribute(SESSION_KEY,imageCode.getCode());
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        ImageIO.write(imageCode.getImage(),"JPEG",outputStream);
        BASE64Encoder base64Encoder =new BASE64Encoder();
        return "data:image/jpg;base64,"+base64Encoder.encode(outputStream.toByteArray());
    }
}
