package com.minisun.todolist.exception;

import com.minisun.todolist.dto.ApiResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.RejectedExecutionException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({IllegalArgumentException.class})
    public ApiResponseDTO<?> handleIllegalArgumentException(IllegalArgumentException ex){
        return new ApiResponseDTO<>(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
    }
    @ExceptionHandler({NullPointerException.class})
    public ApiResponseDTO<?> handleNullPointerException(NullPointerException ex){
        return new ApiResponseDTO<>(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
    }

    @ExceptionHandler({RejectedExecutionException.class})
    public ApiResponseDTO<?> handleRejectedExecutionException(RejectedExecutionException ex){
        return new ApiResponseDTO<>(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    public ApiResponseDTO<?> handleUsernameNotFoundException(UsernameNotFoundException ex){
        return new ApiResponseDTO<>(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
    }

}
