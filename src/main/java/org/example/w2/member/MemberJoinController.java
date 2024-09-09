package org.example.w2.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@WebServlet("/member/join")
@Log4j2
public class MemberJoinController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String uid = req.getParameter("uid");
        String email = req.getParameter("email");

        log.info("UID...................." + uid);

        Object checkObj = session.getAttribute("checkJoin");

        //check success
        try {
            if( MemberDAO.INSTANCE.checkExist(uid,email) == 0) {
                session.setAttribute("checkJoin", true);
                resp.getWriter().println("<script>parent.move()</script>");
            }else {
                session.setAttribute("checkJoin", false);
                resp.getWriter().println("<script>alert('동일한 사용자가 존재합니다.')</script>");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.getRequestDispatcher("/WEB-INF/member/join.jsp").forward(req, resp);

    }
}