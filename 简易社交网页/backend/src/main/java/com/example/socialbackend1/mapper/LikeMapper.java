package com.example.socialbackend1.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LikeMapper {

    // 是否已经点赞
    @Select("SELECT COUNT(*) FROM `like` WHERE post_id=#{postId} AND user_id=#{userId}")
    int isLiked(Long postId, Long userId);

    // 点赞数
    @Select("SELECT COUNT(*) FROM `like` WHERE post_id=#{postId}")
    int getLikeCount(Long postId);

    // 点赞
    @Insert("INSERT INTO `like`(post_id, user_id) VALUES(#{postId}, #{userId})")
    int like(Long postId, Long userId);

    // 取消点赞
    @Delete("DELETE FROM `like` WHERE post_id=#{postId} AND user_id=#{userId}")
    int unlike(Long postId, Long userId);
}