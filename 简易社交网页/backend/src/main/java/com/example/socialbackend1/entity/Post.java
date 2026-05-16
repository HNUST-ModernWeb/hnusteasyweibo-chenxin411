package com.example.socialbackend1.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Post {
    private Long id;
    private String content;
    private Long userId;
    private LocalDateTime createTime;
    private String username;
    private String avatar;
    private String image; // 🔥 加这个
}