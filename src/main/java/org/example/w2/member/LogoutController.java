package org.example.w2.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@WebServlet(value = "/logout")
@Log4j2
public class LogoutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("--------------------------");

        Cookie[] cookies = req.getCookies();

        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("member")) {
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                    break;
                }// end if
            }// end for
        }

//        HttpSession session = req.getSession(false);
//
//        if (session == null) {
//            resp.sendRedirect("/");
//            return;
//        }
//
//        session.removeAttribute("uid");
//        //무효화
//        session.invalidate();
        resp.sendRedirect("/");



    }
}
