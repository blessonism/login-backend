package org.springboot.login_back.controller;

import jakarta.annotation.Resource;
import org.springboot.login_back.domain.User;
import org.springboot.login_back.service.UserService;
import org.springboot.login_back.utils.Result;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:Zikun Zhang
 * @Student ID(NCHU):21224131
 * @Student ID(NCI):X21205833
 * @Date: 2024/5/16 下午9:30
 * @Description:
 **/

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result<User> loginController(@RequestParam String uname, @RequestParam String password){
        User user = userService.loginService(uname, password);
        if(user!=null){
            return Result.success(user,"登录成功！");
        }else{
            return Result.error("123","账号或密码错误！");
        }
    }

    @PostMapping("/register")
    public Result<User> registController(@RequestBody User newUser){
        if (newUser.getRole() == null || newUser.getRole().isEmpty()) {
           newUser.setRole("user");
        }

        User user = userService.registService(newUser);
        if(user!=null){
            return Result.success(user,"注册成功！");
        }else{
            return Result.error("456","用户名已存在！");
        }
    }
}
