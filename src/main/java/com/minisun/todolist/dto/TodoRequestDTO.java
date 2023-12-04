package com.minisun.todolist.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class TodoRequestDTO {
    private String title;
    private String content;

    public TodoRequestDTO(String title,String content){
        this.title = title;
        this.content = content;
    };
}
