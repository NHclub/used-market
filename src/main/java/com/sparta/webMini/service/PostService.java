package com.sparta.webMini.service;

import com.sparta.webMini.dto.*;
import com.sparta.webMini.entity.Post;
import com.sparta.webMini.repository.PostRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PostService {


    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 게시글 작성
    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        Post savePost = postRepository.save(post);
        PostResponseDto postResponseDto = new PostResponseDto(savePost);
        return postResponseDto;
    }
    // 게시글 조회
    public PostListResponseDto getPosts() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostResponseDto> postResponseDtoList = posts.stream()
                .map(post -> new PostResponseDto(post))
                .collect(Collectors.toList());
        return new PostListResponseDto(postResponseDtoList);
    }

    // 내 게시글 조회
    public PostListResponseDto getUserPosts(String username) {
        List<Post> posts = postRepository.findByUsername(username);
        List<PostResponseDto> responseDtoList = new ArrayList<>();
        for (Post post : posts) {
            responseDtoList.add(new PostResponseDto(post));
        }
        return new PostListResponseDto(responseDtoList);
    }

    // 선택한 게시글 상세 조회
    public Post getPost(Long id) {
        // id를 사용하여 해당 게시글을 데이터베이스에서 조회하기
        Post post = postRepository.findById(id).orElseThrow(()->
                new NoSuchElementException("게시글이 존재하지 않습니다."));

        // 조회된 게시글을 반환합니다.
        return post;
    }

    // 게시글 수정
    @Transactional
    public PostUpdateResponseDto updatePost(Long id, PostRequestDto requestDto) {
        Post userPost = postRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("게시글이 존재하지 않습니다."));
        userPost.update(requestDto);
        return new PostUpdateResponseDto(userPost);
    }
//
//
    // 게시글 삭제
    @Transactional
    public AccessMessageDto deletePost(Long id) {
        Post userPost = postRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("게시글이 존재하지 않습니다."));

        postRepository.delete(userPost);
        return new AccessMessageDto("삭제 완료");
    }



}
