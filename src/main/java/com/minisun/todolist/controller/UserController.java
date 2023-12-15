package com.minisun.todolist.controller;

import com.minisun.todolist.dto.ApiResponseDTO;
import com.minisun.todolist.dto.UserRequestDTO;
import com.minisun.todolist.service.UserService;
import com.minisun.todolist.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import com.minisun.todolist.jwt.JwtUtil;

@RequestMapping("/api/users")
@RestController
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserServiceImpl userServiceImpl,JwtUtil jwtUtil){
        this.userService = userServiceImpl;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public ApiResponseDTO<Void> signup(@Valid @RequestBody UserRequestDTO userRequestDto) {
        userService.signup(userRequestDto);
        return new ApiResponseDTO<>(HttpStatus.CREATED.value(),"회원가입 성공");
    }

    @PostMapping("/login")
    public ApiResponseDTO<Void> login(@RequestBody UserRequestDTO userRequestDto, HttpServletResponse response) {
        userService.login(userRequestDto);
        response.setHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(userRequestDto.getUsername()));
        return new ApiResponseDTO<>(HttpStatus.OK.value(),"로그인 성공");
    }
}
