package com.example.socialbackend1.controller;

import com.example.socialbackend1.entity.Comment;
import com.example.socialbackend1.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/comment")
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    // 获取评论
    @GetMapping("/list/{postId}")
    public Map<String, Object> list(@PathVariable Long postId) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("data", commentMapper.getCommentsByPostId(postId));
        return map;
    }

    // 发布评论
    @PostMapping("/publish")
    public Map<String, Object> publish(@RequestBody Comment comment) {
        commentMapper.addComment(comment);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "评论成功");
        return map;
    }
}