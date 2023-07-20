package com.sparta.webMini.entity;

import com.sparta.webMini.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Table(name = "post") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "price", nullable = false)
    private int price;
    @Column(name = "category", nullable = false)
    private String category;
    @Column(name = "location", nullable = false)
    private String location;
    @Column(name = "isSold", nullable = false)
    private boolean isSold;
    @Column(name = "img1")
    private String img1;
    @Column(name = "img2")
    private String img2;
    @Column(name = "img3")
    private String img3;


    @OneToMany(mappedBy = "post",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @OrderBy("createdAt desc")
    private List<Comment> commentList = new ArrayList<>();


    public Post(PostRequestDto requestDto) {
        this.content = requestDto.getContent();
        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.price = requestDto.getPrice();
        this.category = requestDto.getCategory();
        this.location = requestDto.getLocation();
        this.isSold  = requestDto.isSold();

        List<String> images = requestDto.getImages();
        if (images != null && !images.isEmpty()) {
            if (images.size() >= 1) {
                this.img1 = images.get(0);
            }
            if (images.size() >= 2) {
                this.img2 = images.get(1);
            }
            if (images.size() == 3) {
                this.img3 = images.get(2);
            }
        }
    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.username = requestDto.getUsername();
        this.price = requestDto.getPrice();
        this.category = requestDto.getCategory();
        this.location = requestDto.getLocation();
        this.isSold = requestDto.isSold();
        List<String> images = requestDto.getImages();
        if (images != null && !images.isEmpty()) {
            if (images.size() >= 1) {
                this.img1 = images.get(0);
            }
            if (images.size() >= 2) {
                this.img2 = images.get(1);
            }
            if (images.size() == 3) {
                this.img3 = images.get(2);
            }
        }
    }
}