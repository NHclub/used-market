package com.sparta.webMini.controller;

import com.sparta.webMini.dto.*;
import com.sparta.webMini.entity.Post;
import com.sparta.webMini.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 전체 게시글 목록 조회 API
    @GetMapping("/posts")
    public PostListResponseDto getPosts() {
        return postService.getPosts();
    }

    // 내 게시글 목록 조회 API
    @GetMapping("/posts/{username}")
    public PostListResponseDto getUserPosts(@PathVariable String username) {
        return postService.getUserPosts(username);
    }


    // 게시글 생성 API
    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }


    // 선택한 게시글 상세 조회 API
    @GetMapping("/post/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long id) {
        // 해당 게시물을 Post Entity 타입으로 받기
        Post post = postService.getPost(id);

        // 게시물이 없는 경우 404 status code 반환
        if (post == null) {
            return ResponseEntity.notFound().build();
        }

        // PostResponseDto 객체로 변환
        PostResponseDto responseDto = new PostResponseDto(post);

        // 게시물이 있는 경우 200 status code와 게시물 반환
        return ResponseEntity.ok(responseDto);
    }


    //선택한 게시글 수정 API
    @PutMapping("/post/{id}")
    public PostUpdateResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(id, requestDto);
    }

    // 선택한 게시글 삭제 API
    @DeleteMapping("/post/{id}")
    public AccessMessageDto deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }

}
