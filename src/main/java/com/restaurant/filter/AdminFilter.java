package com.restaurant.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AdminFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.startsWith("/manage")) {
            if (request.getSession(false) == null || !"ADMIN".equals(request.getSession().getAttribute("userRole"))) {
                response.sendRedirect("/access-denied");
                return;
            }
        }

        // 요청을 다음 필터나 서블릿으로 전달
        filterChain.doFilter(request, response);
    }
}
