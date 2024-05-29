package org.springboot.login_back;

import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AuthInterceptorTest {
    @Autowired
    private HandlerInterceptor authInterceptor;

    @Test
    public void testAdminAccessWithoutRole() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setRequestURI("/admin");
        boolean result = authInterceptor.preHandle(request, response, new Object());

        assertEquals(false, result);
        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
    }

    @Test
    public void testUserAccessWithoutRole() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setRequestURI("/user");
        boolean result = authInterceptor.preHandle(request, response, new Object());

        assertEquals(false, result);
        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
    }

    @Test
    public void testAdminAccessWithAdminRole() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setRequestURI("/admin");
        request.getSession().setAttribute("role", "ADMIN");
        boolean result = authInterceptor.preHandle(request, response, new Object());

        assertEquals(true, result);
    }
}
