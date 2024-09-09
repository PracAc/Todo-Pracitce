package org.example.w2.todo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.w2.common.StringUtil;
import org.example.w2.todo.dao.TodoDAO;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@WebServlet(value = {"/todo/get", "/todo/edit"})
@Log4j2
public class TodoReadController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Get,Edit Do GET");
        String tnoStr = req.getParameter("tno");
        Integer tno = StringUtil.getInt(tnoStr,-1);

        String uri = req.getRequestURI();
        String path = uri.substring(uri.lastIndexOf("/")+1);

        try {
            Optional<TodoVO> result = TodoDAO.INSTANCE.get(tno);

            TodoVO vo = result.orElseThrow();



            req.setAttribute("todo", vo);
            req.getRequestDispatcher("/WEB-INF/todo/" + path + ".jsp").forward(req, resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Edit Do POST");

        String tnoStr = req.getParameter("tno");
        Integer tno = StringUtil.getInt(tnoStr,-1);
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
                .tno(tno)
                .title(title)
                .writer(writer)
                .dueStr(dueDate)
                .dueDate(dueDateTime)
                .build();

        try {
            boolean result = TodoDAO.INSTANCE.updateDB(vo);
            resp.sendRedirect("/todo/get?tno=" + tno);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
