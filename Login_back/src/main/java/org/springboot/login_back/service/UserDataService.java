package org.springboot.login_back.service;

import org.springboot.login_back.domain.User;

import java.util.List;

/**
 * @Author:Zikun Zhang
 * @Student ID(NCHU):21224131
 * @Student ID(NCI):X21205833
 * @Date: 2024/5/29 上午9:42
 * @Description:
 **/
public interface UserDataService {
    List<User> getAllUsers();

    void saveUser(User user);

    User getUserById(Long id);

    void deleteUser(Long id);

    User getUserByName(String name);

}
