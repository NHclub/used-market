package com.sparta.webMini.entity;

import com.sparta.webMini.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "comment") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "username", nullable = false)
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Comment(CommentRequestDto requestDto,Post post) {
        this.content = requestDto.getContent();
        this.username = requestDto.getUsername();
        this.post = post;
    }

    public void update(CommentRequestDto requestDto,Post post) {
        this.content = requestDto.getContent();
        this.username = requestDto.getUsername();
        this.post = post;
    }
}