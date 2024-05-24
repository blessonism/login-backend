package org.springboot.login_back.service;


import jakarta.annotation.Resource;
import org.springboot.login_back.domain.User;
import org.springframework.stereotype.Service;
import org.springboot.login_back.repository.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author:Zikun Zhang
 * @Student ID(NCHU):21224131
 * @Student ID(NCI):X21205833
 * @Date: 2024/5/16 下午9:24
 * @Description:
 **/

@Service
public class serviceImpl implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserDao userDao;

    @Override
    public User loginService(String uname, String password) {

        logger.debug("Attempting to login with username: {}", uname);
        User user = userDao.findByUnameAndPassword(uname, password);
        if (user != null) {
            logger.debug("Login successful for username: {}", uname);
        } else {
            logger.debug("Login failed for username: {}", uname);
        }
        return user;
        /*
        // 如果账号密码都对则返回登录的用户对象，若有一个错误则返回null
        User user = userDao.findByUnameAndPassword(uname, password);
        // 重要信息置空
        if(user != null){
            user.setPassword("");
        }
        return user;*/
    }

    @Override
    public User registService(User user) {
        //当新用户的用户名已存在时
        if(userDao.findByUname(user.getUname())!=null){
            // 无法注册
            return null;
        }else{
            //返回创建好的用户对象(带uid)
            User newUser = userDao.save(user);
            if(newUser != null){
                newUser.setPassword("");
            }
            return newUser;
        }
    }
}
