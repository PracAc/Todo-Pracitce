package org.example.w2.todo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.w2.todo.dao.TodoDAO;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(value = "/todo/regist")
@Log4j2
public class TodoRegistController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/todo/regist.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String writer = req.getParameter("writer");
        String dueDate = req.getParameter("due_date");
        String dueTime = req.getParameter("due_time");

        // 날짜와 시간을 더해 string 변형
        String dueDateTimeStr = dueDate + "T" + dueTime;

        // 문자열을 LocalDateTime으로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime localDateTime = LocalDateTime.parse(dueDateTimeStr, formatter);

        // LocalDateTime을 Timestamp로 변환
        Timestamp dueDateTime = Timestamp.valueOf(localDateTime);

        TodoVO vo = TodoVO.builder()
                .title(title)
                .writer(writer)
                .dueStr(dueDate)
                .dueDate(dueDateTime)
                .build();

        try {
            int tno = TodoDAO.INSTANCE.insertDB(vo);
            resp.sendRedirect("/todo/list?tno=" + tno);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
