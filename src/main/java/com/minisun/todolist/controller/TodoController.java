package com.minisun.todolist.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;

import com.minisun.todolist.dto.*;
import com.minisun.todolist.security.UserDetailsImpl;
import com.minisun.todolist.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/todos")
@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ApiResponseDTO<TodoResponseDTO> postTodo(@RequestBody TodoRequestDTO todoRequestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        TodoResponseDTO responseDTO = todoService.createTodo(todoRequestDTO, userDetails.getUser());
        return new ApiResponseDTO<>(HttpStatus.CREATED.value(), "Todo 작성 성공", responseDTO);
    }

    @GetMapping("/{todoId}")
    public  ApiResponseDTO<TodoResponseDTO> getTodo(@PathVariable Long todoId) {
        TodoResponseDTO responseDTO = todoService.getTodoDto(todoId);
        return new ApiResponseDTO<>(HttpStatus.OK.value(), "Todo 조회 성공", responseDTO);
    }

    @GetMapping
    public ApiResponseDTO<Map<UserResponseDTO, List<TodoResponseDTO>>> getTodoList() {
        List<TodoListResponseDTO> response = new ArrayList<>();
        Map<UserResponseDTO, List<TodoResponseDTO>> responseDTOMap = todoService.getUserTodoMap();
        responseDTOMap.forEach((key, value) -> response.add(new TodoListResponseDTO(key, value)));
        return new ApiResponseDTO<>(HttpStatus.OK.value(), "TodoList 조회 성공", responseDTOMap);
    }


    @PutMapping("/{todoId}")
    public ApiResponseDTO<TodoResponseDTO> putTodo(@PathVariable Long todoId, @RequestBody TodoRequestDTO todoRequestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        TodoResponseDTO responseDTO = todoService.updateTodo(todoId, todoRequestDTO, userDetails.getUser());
        return new ApiResponseDTO<>(HttpStatus.OK.value(), "Todo 수정 성공", responseDTO);
    }


    @PatchMapping("/{todoId}/complete")
    public ApiResponseDTO<TodoResponseDTO> completeTodo(@PathVariable Long todoId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        TodoResponseDTO responseDTO = todoService.completeTodo(todoId, userDetails.getUser());
        return new ApiResponseDTO<>(HttpStatus.OK.value(), "Todo 완료 성공", responseDTO);
    }
}
