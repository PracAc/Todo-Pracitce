package org.example.w2.member;

import lombok.Cleanup;
import org.example.w2.common.ConncetionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public enum MemberDAO {
    INSTANCE;

    MemberDAO(){}

    public int checkExist(String uid, String email) throws Exception {

        String query = "SELECT COUNT(*) FROM tbl_member WHERE (uid = ? or email=?)";

        @Cleanup Connection connection = ConncetionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, uid);
        ps.setString(2, email);
        @Cleanup ResultSet rs = ps.executeQuery();

        rs.next();

        return rs.getInt(1);

    }

    public Optional<MemberVO> get(String word, String pw) throws Exception {

        String query = """
                select 
                    * 
                from
                    tbl_member
                where
                    (uid = ? or email = ?)
                and
                    upw = ?
                and
                    delFlag = false
                """;

        @Cleanup Connection con = ConncetionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, word);
        ps.setString(2, word);
        ps.setString(3, pw);

        @Cleanup ResultSet rs = ps.executeQuery();

        if ( ! rs.next()) {
            return Optional.empty();
        }

        MemberVO vo = MemberVO.builder()
                .mno(rs.getInt("mno"))
                .uid(rs.getString("uid"))
                .upw(rs.getString("upw"))
                .email(rs.getString("email"))
                .delFalg(rs.getBoolean("delFlag"))
                .build();

        return Optional.of(vo);
    }

    public Integer insert(MemberVO vo) throws Exception{

        String checkQuery = """
                SELECT
                    uid,email 
                FROM
                    tbl_member 
                WHERE
                    uid = ?
                OR
                    email = ?
                """;

        @Cleanup Connection con = ConncetionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(checkQuery);
        ps.setString(1, vo.getUid());
        ps.setString(2, vo.getEmail());
        @Cleanup ResultSet rs0 = ps.executeQuery();

        if (rs0.next()){
            return -1;
        }

        ps.close();

        String query = """
                INSERT INTO tbl_member 
                    (uid,upw,email) 
                VALUES (?,?,?);
                """;

        ps = con.prepareStatement(query);
        ps.setString(1, vo.getUid());
        ps.setString(2, vo.getUpw());
        ps.setString(3, vo.getEmail());
        int count = ps.executeUpdate();

        if(count != 1){
            throw new Exception("error");
        }

        ps.close();

        ps = con.prepareStatement("SELECT LAST_INSERT_ID() FROM tbl_member");
        @Cleanup ResultSet rs1 = ps.executeQuery();
        rs1.next();

        Integer mno = rs1.getInt(1);

        return mno;
    }
}

