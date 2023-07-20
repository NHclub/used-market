package com.sparta.webMini.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PostRequestDto {
    private String title;
    private String content;
    private String username;
    private int price;
    private String category;
    private String location;
    private boolean isSold;
    private List<String> images;
}
