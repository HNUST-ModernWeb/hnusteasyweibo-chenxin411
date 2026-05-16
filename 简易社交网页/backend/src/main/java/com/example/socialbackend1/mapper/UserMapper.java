package com.example.socialbackend1.mapper;

import com.example.socialbackend1.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    // 根据用户名查询
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(String username);

    // 根据ID查询
    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(Long id);

    // 注册
    @Insert("INSERT INTO user(username,password,email,avatar) VALUES(#{username},#{password},#{email},#{avatar})")
    int addUser(User user);

    // 🔥 🔥 🔥 关键修复：这里必须更新 avatar！！！
    @Update("UPDATE user SET username=#{username}, email=#{email}, avatar=#{avatar} WHERE id=#{id}")
    int updateUser(User user);
}