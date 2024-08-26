package com.example.demo.security.filter;

import cn.hutool.core.util.StrUtil;
import com.example.demo.security.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = request.getHeader(JwtUtil.HEADER);
        if (StrUtil.isBlankOrUndefined(token)) {
            chain.doFilter(request, response);
            return;
        }
        token = token.substring(7); // 去掉"Bearer "前缀
        Claims claims = jwtUtil.getClaimsByToken(token);
        if (claims == null) {
            throw new RuntimeException("token错误");
        }
        if (jwtUtil.isTokenExpired(claims.getExpiration())) {
            throw new RuntimeException("token过期");
        }
        String username = claims.getSubject();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, null);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}
