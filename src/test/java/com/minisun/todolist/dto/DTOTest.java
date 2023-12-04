package com.minisun.todolist.dto;

import com.minisun.todolist.entity.Todo;
import com.minisun.todolist.entity.User;
import com.minisun.todolist.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)//순서를 정할 수 있다

public class DTOTest {


    @Mock
    UserRepository userRepository;


    @Test
    @Order(1)
    @DisplayName("todo request test")
    void test1(){
        //given
        String title = "todo1";
        String content = "todo1content";

        //when
        TodoRequestDTO requestDTO = new TodoRequestDTO(title,content);

        //then
        assertNotNull(requestDTO);
        assertEquals(title, requestDTO.getTitle());
        assertEquals(content, requestDTO.getContent());

    }

    @Test
    @Order(2)
    @DisplayName("todo response test")
    void test2(){
        //given
        String title = "todo1";
        String content = "todo1content";
        Todo todo  = new Todo();
        todo.setContent(content);
        todo.setTitle(title);
        User user = new User("username1", "password1") ;
        todo.setUser(user);

        //when
        TodoResponseDTO responseDTO = new TodoResponseDTO(todo);

        //then
        assertNotNull(responseDTO);
        assertEquals(title, responseDTO.getTitle());
        assertEquals(content, responseDTO.getContent());

    }

    @Test
    @Order(3)
    @DisplayName("user request test")
    void test3(){
        //given
        String username = "username1";
        String password = "password1";


        //when
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUsername(username);
        userRequestDTO.setPassword(password);

        //then
        assertNotNull(userRequestDTO);
        assertEquals(username, userRequestDTO.getUsername());
        assertEquals(password, userRequestDTO.getPassword());

    }

    @Test
    @Order(4)
    @DisplayName("user response test")
    void test4(){
        //given
        String username = "username1";
        String password = "password1";
        User user  = new User(username,password);

        //when
        UserResponseDTO userResponseDTO = new UserResponseDTO(user);

        //then
        assertNotNull(userResponseDTO);
        assertEquals(username, userResponseDTO.getUsername());

    }



}
