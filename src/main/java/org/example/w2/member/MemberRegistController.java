package org.example.w2.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.w2.common.StringUtil;

import java.io.IOException;

@WebServlet("/mregist")
@Log4j2
public class MemberRegistController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String mnoStr = req.getParameter("mno");
        int mno = StringUtil.getInt(mnoStr,0);

        req.setAttribute("mno",mno);

        req.getRequestDispatcher("/WEB-INF/regist.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        String upw = req.getParameter("upw");
        String email = req.getParameter("email");

        MemberVO member = MemberVO.builder()
                .uid(uid)
                .upw(upw)
                .email(email)
                .build();

        try {
            Integer mno = MemberDAO.INSTANCE.insert(member);
            String path = mno == -1 ? "/mregist?mno=" + mno : "/login?mno=" + mno;
            resp.sendRedirect(path);
        } catch (Exception e) {
            resp.sendRedirect("/mregist");
        }
    }
}
