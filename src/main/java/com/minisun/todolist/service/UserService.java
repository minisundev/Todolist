package com.minisun.todolist.service;

import com.minisun.todolist.dto.UserRequestDTO;
import com.minisun.todolist.entity.User;

public interface UserService {

    /*
    * 게시글 생성
    * @param UserRequestDTO 회원가입 요청 유저정보
    * @return 회원가입 결과
    */

    public void signup(UserRequestDTO userRequestDto);

    /*
    * 로그인
    * @param UserRequestDTO 로그인 요청 유저정보
    * @return 로그인 결과
    */
    public void login(UserRequestDTO userRequestDto);

}
