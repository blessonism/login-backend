package org.springboot.login_back.mapper;

/**
 * @Author:Zikun Zhang
 * @Student ID(NCHU):21224131
 * @Student ID(NCI):X21205833
 * @Date: 2024/5/24 下午8:02
 * @Description:
 **/

import org.apache.ibatis.annotations.*;
import org.springboot.login_back.domain.User;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user")
    List<User> findAll();

    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUserById(int uid);

    @Select("SELECT * FROM user")
    List<User> getAllUsers();

    @Update("UPDATE user SET uname=#{uname}, role=#{role}, password=#{password} WHERE id=#{id}")
    void update(User user);

    @Delete("DELETE FROM user WHERE id=#{id}")
    void delete(Long id);

    @Select("SELECT * FROM user WHERE id=#{id}")
    User findById(Long id);

    @Select("SELECT * FROM user WHERE uname=#{uname}")
    User findByName(String name);

    @Insert("INSERT INTO user(uname, password, role) VALUES(#{uname}, #{password}, #{role})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);
}