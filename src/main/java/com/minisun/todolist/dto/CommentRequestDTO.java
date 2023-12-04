package com.minisun.todolist.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDTO {
    private Long todoId;
    private String text;
}
