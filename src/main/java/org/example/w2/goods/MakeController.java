package org.example.w2.goods;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@WebServlet(value = "/goods/make")
@Log4j2
public class MakeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("doGet");

        Cookie goodsCookie = new Cookie("goods", "123456789");
//        goodsCookie.setPath("/");

        // 1 hour
        goodsCookie.setMaxAge(60*60);

        resp.addCookie(goodsCookie);

    }
}
