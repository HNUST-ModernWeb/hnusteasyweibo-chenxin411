package com.example.socialbackend1.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Like {
    private Long id;
    private Long postId;
    private Long userId;
    private LocalDateTime createTime;
}