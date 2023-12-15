package com.minisun.todolist.service;

import com.minisun.todolist.dto.TodoRequestDTO;
import com.minisun.todolist.dto.TodoResponseDTO;
import com.minisun.todolist.dto.UserResponseDTO;
import com.minisun.todolist.entity.Todo;
import com.minisun.todolist.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;

public interface TodoService {
    /*
    * Todo 생성
    * @param requestDto Todo 생성 요청정보
    * @param user Todo 생성 요청자
    * @return Todo 생성 결과
    */
    public TodoResponseDTO createTodo(TodoRequestDTO dto, User user);

    /*
     * Todo 조회
     * @param todoId Todo id
     * @return Todo 내용
     */
    public TodoResponseDTO getTodoDto(Long todoId);

    /*
     * TodoList 조회
     * @return user와 Todo의 Map
     */
    public Map<UserResponseDTO, List<TodoResponseDTO>> getUserTodoMap();

    /*
     * Todo 수정
     * @param requestDto Todo 수정 요청정보
     * @param user Todo 수정 요청자
     * @return Todo 수정 결과
     */
    @Transactional
    public TodoResponseDTO updateTodo(Long todoId, TodoRequestDTO todoRequestDTO, User user);

    /*
     * Todo 완료
     * @param id 완료할 Todo id
     * @param user Todo 완료 요청자
     * @return Todo 완료 결과
     */
    @Transactional
    public TodoResponseDTO completeTodo(Long todoId, User user);

    /*
     * Todo 가져오기
     * @param id Todo id
     * @return Todo
     */
    public Todo getTodo(Long todoId);

}
