package com.example.socialbackend1.controller;

import com.example.socialbackend1.mapper.LikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/like")
@CrossOrigin
public class LikeController {

    @Autowired
    private LikeMapper likeMapper;

    // 点赞 / 取消点赞
    @PostMapping("/toggle/{postId}")
    public Map<String, Object> toggle(@PathVariable Long postId, @RequestParam Long userId) {
        Map<String, Object> map = new HashMap<>();

        int liked = likeMapper.isLiked(postId, userId);
        if (liked > 0) {
            likeMapper.unlike(postId, userId);
            map.put("msg", "取消点赞");
        } else {
            likeMapper.like(postId, userId);
            map.put("msg", "点赞成功");
        }
        map.put("code", 200);
        return map;
    }

    // 获取点赞数 + 是否点赞
    @GetMapping("/info/{postId}")
    public Map<String, Object> info(@PathVariable Long postId, @RequestParam Long userId) {
        Map<String, Object> map = new HashMap<>();
        int count = likeMapper.getLikeCount(postId);
        int liked = likeMapper.isLiked(postId, userId);
        map.put("code", 200);
        map.put("count", count);
        map.put("liked", liked > 0);
        return map;
    }
}