package org.springboot.login_back.service.Impl;

import org.apache.ibatis.annotations.Mapper;
import org.springboot.login_back.domain.User;
import org.springboot.login_back.mapper.UserMapper;
import org.springboot.login_back.service.UserDataService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:Zikun Zhang
 * @Student ID(NCHU):21224131
 * @Student ID(NCI):X21205833
 * @Date: 2024/5/29 上午9:42
 * @Description:
 **/

@Service
public class UserDataServiceImpl implements UserDataService {

    @Mapper
    private final UserMapper userMapper;

    public UserDataServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> getAllUsers() {
        return userMapper.findAll();
    }


    public void saveUser(User user) {
        if (user.getId() == 0L) {
            userMapper.insert(user);
        } else {
            userMapper.update(user);
        }
    }

    public User getUserById(Long id) {
        return userMapper.findById(id);
    }

    public User getUserByName(String name) {
        return userMapper.findByName(name);
    }

    public void deleteUser(Long id) {
        userMapper.delete(id);
    }
}
