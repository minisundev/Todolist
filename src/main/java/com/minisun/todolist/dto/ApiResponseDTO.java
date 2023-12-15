package com.minisun.todolist.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponseDTO<T> {

    private int status;
    private String message;
    private T data;

    public ApiResponseDTO(int status, String message, T data){
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ApiResponseDTO(int status, String message){
        this.status = status;
        this.message = message;
        this.data = null;
    }

    public ApiResponseDTO(T data){
        this.data = data;
    }
}
