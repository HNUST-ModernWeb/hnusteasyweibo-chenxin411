package com.example.socialbackend1.mapper;

import com.example.socialbackend1.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    // 查询某条动态的评论
    @Select("SELECT * FROM comment WHERE post_id = #{postId} ORDER BY create_time ASC")
    List<Comment> getCommentsByPostId(Long postId);

    // 发布评论
    @Insert("INSERT INTO comment(post_id, user_id, content, username, avatar) " +
            "VALUES(#{postId}, #{userId}, #{content}, #{username}, #{avatar})")
    int addComment(Comment comment);
}