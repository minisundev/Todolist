package com.minisun.todolist.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import com.minisun.todolist.entity.User;


@Getter
@Setter
@EqualsAndHashCode
public class UserResponseDTO {
    private String username;

    public UserResponseDTO(User user) {
        this.username = user.getUsername();
    }
}
