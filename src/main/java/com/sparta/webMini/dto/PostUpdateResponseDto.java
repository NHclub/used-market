package com.sparta.webMini.dto;

import com.sparta.webMini.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostUpdateResponseDto {
    private Long postId;
    private String title;
    private String content;
    private String username;
    private int price;
    private String category;
    private String location;
    private List<String> images;
    private boolean isSold;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> commentList;


    public PostUpdateResponseDto(Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.username = post.getUsername();
        this.price = post.getPrice();
        this.category = post.getCategory();
        this.location = post.getLocation();
        this.isSold = post.isSold();
        this.images = new ArrayList<>();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();

        if (post.getImg1() != null) {
            this.images.add(post.getImg1());
        }
        if (post.getImg2() != null) {
            this.images.add(post.getImg2());
        }
        if (post.getImg3() != null) {
            this.images.add(post.getImg3());
        }

        this.commentList = post.getCommentList().stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }
}
