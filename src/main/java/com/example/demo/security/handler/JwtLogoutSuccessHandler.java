package com.example.demo.security.handler;

import com.example.demo.security.util.JwtUtil;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class JwtLogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, authentication);
        }
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setHeader(JwtUtil.HEADER, "");
        SecurityContextHolder.clearContext();
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        outputStream.write("登录成功".getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
