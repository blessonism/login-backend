package org.springboot.login_back.controller;

import jakarta.annotation.Resource;
import org.springboot.login_back.domain.User;
import org.springboot.login_back.service.UserDataService;
import org.springboot.login_back.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:Zikun Zhang
 * @Student ID(NCHU):21224131
 * @Student ID(NCI):X21205833
 * @Date: 2024/5/29 下午1:05
 * @Description:
 **/

@RestController
@RequestMapping("/users")
public class UserDataController {

    @Resource
    private UserDataService userDataService;

    @GetMapping
    public List<User> getAllUsers() {
        return userDataService.getAllUsers();
    }

    @PostMapping
    public Result createUser(@RequestBody User newUser) {
        if (newUser.getRole() == null || newUser.getRole().isEmpty()) {
            newUser.setRole("USER");
        }

        if(newUser!=null && userDataService.getUserByName(newUser.getUname()) == null){
            userDataService.saveUser(newUser);
            return Result.success(newUser, "添加成功！");
        }else{
            return Result.error("456", "用户名已存在！");
        }
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        userDataService.saveUser(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userDataService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userDataService.getUserById(id);
    }
}
