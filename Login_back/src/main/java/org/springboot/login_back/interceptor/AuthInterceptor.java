package org.springboot.login_back.interceptor;

/**
 * @Author:Zikun Zhang
 * @Student ID(NCHU):21224131
 * @Student ID(NCI):X21205833
 * @Date: 2024/5/20 上午1:27
 * @Description:
 **/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 假设用户登录状态保存在会话中的 "user" 属性
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
/*            // 用户未登录，重定向到登录页面
            logger.info("No user found in session, redirecting to login page.");
            response.sendRedirect("/login");*/
            return true; // 返回false表示请求中断
        }
        logger.info("User logged in, proceeding with request.");
        return true; // 用户已登录，继续处理请求
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        // 请求处理之后进行调用，但在视图被渲染之前（这里不用实现）
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {
        // 在整个请求结束之后被调用，也就是在DispatcherServlet渲染了对应的视图之后执行（这里不用实现）
    }
}