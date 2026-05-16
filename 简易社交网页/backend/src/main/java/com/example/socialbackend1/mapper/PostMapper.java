package com.example.socialbackend1.mapper;

import com.example.socialbackend1.entity.Post;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    @Select("SELECT p.*, u.username, u.avatar FROM post p LEFT JOIN user u ON p.user_id = u.id ORDER BY p.create_time DESC")
    List<Post> getAllPosts();

    @Select("SELECT p.*, u.username, u.avatar FROM post p LEFT JOIN user u ON p.user_id = u.id WHERE p.user_id = #{userId} ORDER BY p.create_time DESC")
    List<Post> getPostsByUserId(Long userId);

    // 🔥 发布带图片
    @Insert("INSERT INTO post(content, user_id, image, create_time) VALUES(#{content}, #{userId}, #{image}, NOW())")
    int addPost(Post post);

    @Delete("DELETE FROM post WHERE id = #{id}")
    int deletePost(Long id);
}