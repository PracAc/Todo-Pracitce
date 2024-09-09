package org.example.w2.todo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.w2.common.PagingInfo;
import org.example.w2.common.StringUtil;
import org.example.w2.todo.dao.TodoDAO;

import java.io.IOException;
import java.util.List;

@WebServlet (value = "/todo/list")
@Log4j2
public class TodoListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("List Do GET");

        String pageStr = req.getParameter("page");
        Integer page = StringUtil.getInt(pageStr,1);

        try{
            Integer total = TodoDAO.INSTANCE.getTotal();

            PagingInfo pageInfo = new PagingInfo(page,10,total);

            log.info(pageInfo);
            List<TodoVO> todoList = TodoDAO.INSTANCE.list(pageInfo.getPage());

            req.setAttribute("list",todoList);
            req.setAttribute("pageInfo",pageInfo);

        }catch (Exception e){
            e.printStackTrace();
        }


        req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req, resp);
    }

}
