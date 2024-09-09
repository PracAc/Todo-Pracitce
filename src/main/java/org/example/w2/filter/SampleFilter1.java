package org.example.w2.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@WebFilter(urlPatterns = "/mypage")
@Log4j2
public class SampleFilter1 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Sample Filter 1 RUN-------------------------------------------------------");



        // 다음 동작 진행
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
