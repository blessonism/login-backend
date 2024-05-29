package org.springboot.login_back.interceptor;

/**
 * @Author:Zikun Zhang
 * @Student ID(NCHU):21224131
 * @Student ID(NCI):X21205833
 * @Date: 2024/5/20 上午1:27
 * @Description:
 **/

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String role = (String) request.getSession().getAttribute("role");
        String requestURI = request.getRequestURI();

        logger.info("Intercepted request to: " + requestURI);
        logger.info("User role: " + role);

        // 允许对 /login 请求的处理
        if (requestURI.equals("/login")) {
            return true;
        }

        if (requestURI.startsWith("/admin") && !"ADMIN".equals(role)) {
            logger.warn("Unauthorized access to admin area");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("WWW-Authenticate", "Basic realm=\"Access to admin area\"");
            return false;
        } else if (requestURI.startsWith("/user") && role == null) {
            logger.warn("Unauthorized access to user area");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("WWW-Authenticate", "Basic realm=\"Access to user area\"");
            return false;
        }
        return true;
    }
}