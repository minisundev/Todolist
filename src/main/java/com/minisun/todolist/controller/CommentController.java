package com.minisun.todolist.controller;

import com.minisun.todolist.dto.ApiResponseDTO;
import com.minisun.todolist.dto.CommentRequestDTO;
import com.minisun.todolist.dto.CommentResponseDTO;
import com.minisun.todolist.dto.CommonResponseDTO;
import com.minisun.todolist.service.CommentService;
import com.minisun.todolist.service.CommentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minisun.todolist.security.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/comments")
@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentServiceImpl commentServiceImpl){
        this.commentService = commentServiceImpl;
    }

    @PostMapping
    public ApiResponseDTO<CommentResponseDTO> postComment(@RequestBody CommentRequestDTO commentRequestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponseDTO responseDTO = commentService.createComment(commentRequestDTO, userDetails.getUser());
        return new ApiResponseDTO<>(HttpStatus.CREATED.value(), "Comment 작성 성공", responseDTO);
    }

    @PutMapping("/{commentId}")
    public ApiResponseDTO<CommonResponseDTO> putComment(@PathVariable Long commentId, @RequestBody CommentRequestDTO commentRequestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponseDTO responseDTO = commentService.updateComment(commentId, commentRequestDTO, userDetails.getUser());
        return new ApiResponseDTO<>(HttpStatus.OK.value(), "Comment 수정 성공", responseDTO);
    }


    @DeleteMapping("/{commentId}")
    public ApiResponseDTO<Void> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(commentId, userDetails.getUser());
        return new ApiResponseDTO<>(HttpStatus.OK.value(), "Comment 삭제 성공");
    }
}
