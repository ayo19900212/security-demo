package com.example.demo.security.handler;

import com.example.demo.security.util.JwtUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        String token = jwtUtil.generateToken(authentication.getName());
        httpServletResponse.setHeader(JwtUtil.HEADER, token);
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        outputStream.write("登录成功".getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
