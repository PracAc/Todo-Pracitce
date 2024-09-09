package org.example.w2.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Arrays;

@WebServlet(value = "/mypage")
@Log4j2
public class MyPageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("doGet");

        //기존에 뭔가 없었으면 새로 만들지 마세요

//        HttpSession session = req.getSession(false);
//
//        if (session == null || session.getAttribute("uid") == null) {
//            resp.sendRedirect("/login");
//            return;
//        }

        Cookie[] cks = req.getCookies();

        if (cks == null || cks.length == 0) {
            resp.sendRedirect("/login");
            return;
        }

//        resp.sendRedirect("/mypage.jsp");

        req.getRequestDispatcher("/WEB-INF/mypage.jsp").forward(req, resp);
    }
}
