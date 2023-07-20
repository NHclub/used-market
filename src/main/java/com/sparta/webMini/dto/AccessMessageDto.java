package com.sparta.webMini.dto;

import lombok.Getter;

@Getter
public class AccessMessageDto {
    private String message;

    public AccessMessageDto(String message) {
        this.message = message;
    }
}
