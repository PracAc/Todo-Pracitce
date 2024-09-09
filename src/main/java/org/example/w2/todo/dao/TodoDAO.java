package org.example.w2.todo.dao;

import lombok.Cleanup;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.example.w2.common.ConncetionUtil;
import org.example.w2.todo.TodoVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@ToString
public enum TodoDAO {
    INSTANCE;

    TodoDAO(){

    }

    public List<TodoVO> list(int page) throws Exception{

        int skip = (page - 1) * 10;

        String query = """
                SELECT *
                FROM
                    tbl_todo_v2
                WHERE
                    tno > 0
                AND
                    delflag = false
                AND
                    finish = false
                ORDER BY
                    tno DESC
                LIMIT ?,10
                """;

        @Cleanup Connection con = ConncetionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, skip);
//        ps.setInt(2, size);
        @Cleanup ResultSet rs = ps.executeQuery();

        List<TodoVO> list = new ArrayList<>();

        while (rs.next()) {
            TodoVO vo = TodoVO.builder()
                    .tno(rs.getInt("tno"))
                    .title(rs.getString("title"))
                    .writer(rs.getString("writer"))
                    .dueStr(rs.getString("dueStr"))
                    .dueDate(rs.getTimestamp("dueDate"))
                    .finish(rs.getBoolean("finish"))
                    .delflag(rs.getBoolean("delflag"))
                    .build();

            list.add(vo);
        }//end while

        return list;
    }

    public Integer getTotal() throws Exception{
        String query = """
                SELECT COUNT(*)
                FROM
                    tbl_todo_v2
                WHERE
                    tno > 0
                AND
                    delflag = false
                AND
                    finish = false
                """;

        @Cleanup Connection con = ConncetionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        @Cleanup ResultSet rs = ps.executeQuery();

        rs.next();

        Integer total = rs.getInt(1);
        return total;
    }

    public int insertDB(TodoVO vo) throws Exception{
        String query = """
                INSERT INTO 
                    tbl_todo_v2
                    (title,writer,dueDate,dueStr)
                VALUES (
                           ?,
                           ?,
                           ?,
                           ?
                );
                """;

        @Cleanup Connection con = ConncetionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, vo.getTitle());
        ps.setString(2, vo.getWriter());
        ps.setTimestamp(3, vo.getDueDate());
        ps.setString(4, vo.getDueStr());

        int count = ps.executeUpdate();

        if(count != 1){
            throw new Exception("mol! lu!");
        }

        ps.close();

        ps = con.prepareStatement("SELECT LAST_INSERT_ID()");
        @Cleanup ResultSet rs = ps.executeQuery();
        rs.next();

        Integer tno = rs.getInt(1);

        con.commit();
        con.setAutoCommit(true);

        return tno;
    }

    public Optional<TodoVO> get(Integer tno) throws Exception{

        log.info("tno: "+tno);

        String query = """
                SELECT
                    tno,title,writer,dueStr,finish,delflag,
                    TIME (dueDate) AS dueTime
                FROM
                    tbl_todo_v2
                WHERE tno = ?
                """;

        @Cleanup Connection con = ConncetionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, tno);

        @Cleanup ResultSet rs = ps.executeQuery();

        if( ! rs.next()){
            return Optional.empty();
        }

        TodoVO vo = TodoVO.builder()
                .tno(rs.getInt("tno"))
                .title(rs.getString("title"))
                .writer(rs.getString("writer"))
                .dueStr(rs.getString("dueStr"))
                .dueTime(rs.getTime("dueTime"))
                .finish(rs.getBoolean("finish"))
                .delflag(rs.getBoolean("delflag"))
                .build();

        log.info(vo);

        return Optional.of(vo);
    }

    public boolean updateDB(TodoVO vo) throws Exception{

        String query = """
                UPDATE 
                    tbl_todo_v2
                SET
                    title = ?,
                    writer = ?,
                    dueDate = ?,
                    dueStr = ?,
                    moddate = NOW()
                WHERE tno = ?
                """;

        @Cleanup Connection con = ConncetionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, vo.getTitle());
        ps.setString(2, vo.getWriter());
        ps.setTimestamp(3, vo.getDueDate());
        ps.setString(4, vo.getDueStr());
        ps.setInt(5, vo.getTno());

        int count = ps.executeUpdate();
        if(count == 1){
            return true;
        }

        return false;
    }
}
