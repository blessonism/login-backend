package org.springboot.login_back.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springboot.login_back.domain.User;
import org.springboot.login_back.service.UserService;
import org.springboot.login_back.utils.Result;
import org.springframework.web.bind.annotation.*;

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
    public Result login(@RequestParam("uname") String uname, @RequestParam("password") String password, HttpServletRequest request) {

        User user = userService.loginService(uname, password);
        if (user != null) {
            request.getSession().setAttribute("role", user.getRole());
            return Result.success(user, "登录成功！");
        } else {
            return Result.error("123", "账号或密码错误！");
        }
    }

        @PostMapping("/register")
    public Result register(@RequestBody User newUser){
        if (newUser.getRole() == null || newUser.getRole().isEmpty()) {
            newUser.setRole("USER");
        }

        User user = userService.registService(newUser);
        if(user!=null){
            return Result.success(user, "注册成功！");
        }else{
            return Result.error("456", "用户名已存在！");
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "logged out";
    }
}
