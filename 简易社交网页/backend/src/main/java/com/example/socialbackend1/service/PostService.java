package com.example.socialbackend1.service;

import com.example.socialbackend1.entity.Post;
import com.example.socialbackend1.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostMapper postMapper;

    public int addPost(Post post) {
        return postMapper.addPost(post);
    }

    public List<Post> getAllPosts() {
        return postMapper.getAllPosts();
    }

    public List<Post> getPostsByUserId(Long userId) {
        return postMapper.getPostsByUserId(userId);
    }
}