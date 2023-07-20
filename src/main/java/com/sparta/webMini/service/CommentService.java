package com.sparta.webMini.service;

import com.sparta.webMini.dto.CommentDeleteDto;
import com.sparta.webMini.dto.CommentRequestDto;
import com.sparta.webMini.dto.CommentResponseDto;
import com.sparta.webMini.entity.Comment;
import com.sparta.webMini.entity.Post;
import com.sparta.webMini.repository.CommentRepository;
import com.sparta.webMini.repository.PostRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


    // 댓글 작성
    public CommentResponseDto createComment(CommentRequestDto requestDto) {

        Post post = postRepository.findById(requestDto.getPostId())
                .orElseThrow(() -> new NoSuchElementException( requestDto.getPostId() + "번 게시글을 찾을 수 없습니다."));
        // RequestDto -> Entity
        Comment comment = new Comment(requestDto,post);
        //DB저장
        Comment saveComment = commentRepository.save(comment);
        // Entity -> ResponseDto
        CommentResponseDto commentResponseDto = new CommentResponseDto(saveComment);
        return commentResponseDto;
    }

    //댓글 수정
    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException( id + "번 게시글을 찾을 수 없습니다."));
        //해당 메모가 DB에 존재하는지 확인
        Comment comment = findComment(id);
        // DB수정
        comment.update(requestDto,post);
        //Entity -> ResponseDto
        CommentResponseDto responseDto = new CommentResponseDto(comment);
        return responseDto;
    }

    // 댓글 삭제
    public CommentDeleteDto deleteComment(Long id) {
        //해당 메모가 DB에 존재하는지 확인
        Comment comment = findComment(id);
        commentRepository.delete(comment);
        CommentDeleteDto commentDeleteDto = new CommentDeleteDto();
        return commentDeleteDto;
    }

    // 댓글 가져오기
    private Comment findComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("선택한 댓글은 존재하지 않습니다."));
        return comment;
    }
}
