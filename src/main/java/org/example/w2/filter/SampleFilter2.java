package org.example.w2.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.w2.common.CookieUtil;

import java.io.IOException;

@WebFilter(urlPatterns = "/mypage")
@Log4j2
public class SampleFilter2 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       log.info("Sample Filter 2 RUN ----------------------------------");

       HttpServletRequest req = (HttpServletRequest) servletRequest;
       HttpServletResponse resp = (HttpServletResponse) servletResponse;

       if(CookieUtil.getCookie(req,"member") == null){
           resp.sendRedirect("/login");
           return;
       }

       //다음동작 진행
       filterChain.doFilter(servletRequest, servletResponse);
    }
}
