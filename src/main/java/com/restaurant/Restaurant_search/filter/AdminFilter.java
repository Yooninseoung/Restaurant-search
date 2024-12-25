package com.restaurant.Restaurant_search.filter;

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
            if (uri.startsWith("/manage")) {
                if (request.getSession(false) == null || !"admin".equals(request.getSession().getAttribute("userId"))) {
                    // 관리자 페이지 접근 거부 시 HTML 콘텐츠 직접 출력
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write("<html lang=\"en\">\n" +
                            "<head>\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <title>불가능한 접근</title>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "<h1>일반 사용자는 접근할 수 없습니다.</h1>\n" +
                            "<hr/>\n" +
                            "<h3>관리자 전용 페이지입니다.</h3>\n" +
                            "<ul>\n" +
                            "    <li>관리자 계정으로 접근하였는지 확인하세요</li>\n" +
                            "</ul>\n" +
                            "<a href=\"/\">홈페이지로 돌아가기</a>\n" +
                            "</body>\n" +
                            "</html>");
                    response.getWriter().flush();
                    return;
                }
                }
            }


            // 요청을 다음 필터나 서블릿으로 전달
        filterChain.doFilter(request, response);
    }
}
