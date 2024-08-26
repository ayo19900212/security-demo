package com.example.demo.controller;

import com.example.demo.common.exception.BaseException;
import com.example.demo.common.pojo.ResultDTO;
import com.example.demo.dal.dataobject.User;
import com.example.demo.security.util.JwtUtil;
import com.example.demo.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.example.demo.common.enums.ResponseCodeEnum.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserService userService;

    @PostMapping(value = "/register")
    public ResultDTO<Boolean> register(@RequestBody User user) {
        String username = user.getUsername();
        User exist = userService.queryByUsername(username);
        if (exist != null) {
            return ResultDTO.error(USERNAME_EXISTS);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResultDTO.success(userService.register(user));
    }

    @PostMapping(value = "/login")
    public ResultDTO<Map<String, String>> login(@RequestBody User user, HttpServletResponse response) {
        User exist = userService.queryByUsername(user.getUsername());
        if (exist != null) {
            boolean matches = passwordEncoder.matches(user.getPassword(), exist.getPassword());
            if (matches) {
                // 生成JWT
                String token = jwtUtil.generateToken(user.getUsername());
                response.setHeader(JwtUtil.HEADER, token);
                response.setHeader("Access-control-Expost-Headers", JwtUtil.HEADER);
                Map<String, String> map = new HashMap<>();
                map.put("token", token);
                return ResultDTO.success(map);
            } else {
                throw new BaseException(PASSWORD_ERROR);
            }
        } else {
            throw new BaseException(USERNAME_NOT_EXISTS);
        }
    }

    /**
     * 用户登出
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/logout")
    public ResultDTO<Boolean> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return ResultDTO.success(true);
    }

    @GetMapping("/test")
    public String test() {
        return "test success";
    }
}
