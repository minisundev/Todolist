package com.minisun.todolist.service;


import com.minisun.todolist.dto.UserRequestDTO;
import com.minisun.todolist.entity.User;
import com.minisun.todolist.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 서버의 PORT 를 랜덤으로 설정합니다.
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 테스트 인스턴스의 생성 단위를 클래스로 변경
///-> 각각의 test들이 필드를 공유할 수 있게 된다
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)//순서를 정할 수 있다

public class UserServiceImplTest {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;



    @Test
    @Order(1)
    @DisplayName("signup test")
    @Rollback
    void test1() {
        // given
        String username = "user1";
        String password = "user1password";
        UserRequestDTO requestDTO = new UserRequestDTO();
        requestDTO.setPassword(password);
        requestDTO.setUsername(username);

        // when
        userServiceImpl.signup(requestDTO);

        // then
        User user = userRepository.findById(1L).orElse(null);
        assertNotNull(user.getId());
        assertEquals(passwordEncoder.encode(password), user.getPassword());
        assertEquals(username, user.getUsername());
    }
}
