package com.minisun.todolist.service;


import com.minisun.todolist.dto.TodoRequestDTO;
import com.minisun.todolist.dto.TodoResponseDTO;
import com.minisun.todolist.entity.User;
import com.minisun.todolist.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 서버의 PORT 를 랜덤으로 설정합니다.
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 테스트 인스턴스의 생성 단위를 클래스로 변경
///-> 각각의 test들이 필드를 공유할 수 있게 된다
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)//순서를 정할 수 있다
public class TodoServiceImplTest {

    @Autowired
    TodoServiceImpl todoServiceImpl;
    @Autowired
    UserRepository userRepository;

    @Test
    @Order(1)
    @DisplayName("createTodo")
    void test1() {
        // given
        String title = "Todo1";
        String content = "Todo1 Content";
        TodoRequestDTO requestDTO = new TodoRequestDTO(
                title,
                content
        );
        User user = userRepository.findById(1L).orElse(null);

        // when
        TodoResponseDTO todo = todoServiceImpl.createTodo(requestDTO,user);

        // then
        assertNotNull(todo.getId());
        assertEquals(title, todo.getTitle());
        assertEquals(content, todo.getContent());
    }
}
