package com.sparta.webMini.dto;

import com.sparta.webMini.entity.Post;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    private Long postId;
    private String content;
    private String username;
}
