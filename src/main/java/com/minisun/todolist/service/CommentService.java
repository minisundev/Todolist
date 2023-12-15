package com.minisun.todolist.service;

import com.minisun.todolist.dto.CommentRequestDTO;
import com.minisun.todolist.dto.CommentResponseDTO;
import com.minisun.todolist.entity.Comment;
import com.minisun.todolist.entity.Todo;
import com.minisun.todolist.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.RejectedExecutionException;

public interface CommentService {

    /*
     * Comment 생성
     * @param requestDto Comment 생성 요청정보
     * @param user Comment 생성 요청자
     * @return Comment 생성 결과
     */
    public CommentResponseDTO createComment(CommentRequestDTO dto, User user);

    /*
     * Comment 수정
     * @param requestDto Comment 수정 요청정보
     * @param user Comment 수정 요청자
     * @return Comment 수정 결과
     */
    public CommentResponseDTO updateComment(Long commentId, CommentRequestDTO commentRequestDTO, User user);

    /*
     * Comment 삭제
     * @param id 삭제할 Comment id
     * @param user Comment 삭제 요청자
     * @return Comment 삭제 결과
     */
    public void deleteComment(Long commentId, User user);

}
