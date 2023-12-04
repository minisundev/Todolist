package com.minisun.todolist.dto;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class TodoListResponseDTO {
    private UserResponseDTO user;
    private List<TodoResponseDTO> todoList;

    public TodoListResponseDTO(UserResponseDTO user, List<TodoResponseDTO> todoList) {
        this.user = user;
        this.todoList = todoList;
    }
}
