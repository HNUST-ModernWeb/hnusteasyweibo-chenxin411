package com.example.socialbackend1.controller;

import com.example.socialbackend1.entity.Post;
import com.example.socialbackend1.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post")
@CrossOrigin
public class PostController {

    @Autowired
    private PostMapper postMapper;

    @PostMapping("/publish")
    public Map<String, Object> publish(@RequestBody Post post) {
        Map<String, Object> map = new HashMap<>();
        postMapper.addPost(post);
        map.put("code", 200);
        map.put("msg", "发布成功");
        return map;
    }

    @GetMapping("/list")
    public Map<String, Object> getList() {
        Map<String, Object> map = new HashMap<>();
        List<Post> posts = postMapper.getAllPosts();
        map.put("code", 200);
        map.put("data", posts);
        return map;
    }

    @GetMapping("/user/{userId}")
    public Map<String, Object> getUserPosts(@PathVariable Long userId) {
        Map<String, Object> map = new HashMap<>();
        List<Post> posts = postMapper.getPostsByUserId(userId);
        map.put("code", 200);
        map.put("data", posts);
        return map;
    }
    @DeleteMapping("/delete/{id}")
    public Map<String, Object> deletePost(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<>();
        postMapper.deletePost(id);
        map.put("code", 200);
        map.put("msg", "删除成功");
        return map;
    }
}