package org.example.w2.goods;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@WebServlet(value = "/goods/view1")
@Log4j2
public class ViewController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();

        if (cookies == null) {
            log.info("cookies is null");
        }

        for (Cookie cookie : cookies) {
            log.info("cookie: " + cookie.getName());
            log.info("cookieValue: " + cookie.getValue());
            log.info("-----------------------------------------");
        }

    }
}
