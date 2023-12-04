package com.minisun.todolist.service;

import java.util.concurrent.RejectedExecutionException;

import com.minisun.todolist.dto.CommentRequestDTO;
import com.minisun.todolist.dto.CommentResponseDTO;
import com.minisun.todolist.entity.Comment;
import com.minisun.todolist.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minisun.todolist.entity.User;
import com.minisun.todolist.entity.Todo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TodoService todoService;

    public CommentResponseDTO createComment(CommentRequestDTO dto, User user) {
        Todo todo = todoService.getTodo(dto.getTodoId());

        Comment comment = new Comment(dto);
        comment.setUser(user);
        comment.setTodo(todo);

        commentRepository.save(comment);

        return new CommentResponseDTO(comment);
    }

    @Transactional
    public CommentResponseDTO updateComment(Long commentId, CommentRequestDTO commentRequestDTO, User user) {
        Comment comment = getUserComment(commentId, user);

        comment.setText(commentRequestDTO.getText());

        return new CommentResponseDTO(comment);
    }

    public void deleteComment(Long commentId, User user) {
        Comment comment = getUserComment(commentId, user);

        commentRepository.delete(comment);
    }

    private Comment getUserComment(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글 ID 입니다."));

        if(!user.getId().equals(comment.getUser().getId())) {
            throw new RejectedExecutionException("작성자만 수정할 수 있습니다.");
        }
        return comment;
    }
}
