package com.example.socialbackend1.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class User {
    private Long id;
    private String username;  // 对应数据库 username
    private String password;
    private String avatar;
    private String email;
    private LocalDateTime createTime;  // 对应数据库 create_time
}