package com.minisun.todolist.dto;

import java.time.LocalDateTime;

import com.minisun.todolist.entity.Comment;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class CommentResponseDTO extends CommonResponseDTO{
    private Long id;
    private String text;
    private UserResponseDTO user;
    private LocalDateTime createDate;

    public CommentResponseDTO(Comment comment) {
        this.id = comment.getId();
        this.text = comment.getText();
        this.user = new UserResponseDTO(comment.getUser());
        this.createDate = comment.getCreateDate();
    }
}
