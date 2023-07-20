package com.sparta.webMini.dto;

import lombok.Getter;

@Getter
public class CommentDeleteDto {
    public String msg;
    public int statusCode;

    public CommentDeleteDto() {
        this.msg = "댓글 삭제 성공";
        this.statusCode = 200;
    }
}
