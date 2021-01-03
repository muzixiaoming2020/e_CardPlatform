package jsu.dao;

import jsu.bean.EmployeeBasicinfo;
import jsu.bean.Message;
import jsu.bean.MessageList;
import jsu.util.DBUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class MessageDAO {
    public int getSum(){
        Connection conn = DBUtil.getConnection();
        String sql = "select count(*) from message";
        Statement s = null;
        int sum = 0;
        try {
            s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                sum = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.closeJDBC(null, s, conn);
        }
        return sum;
    }
    public void add(Message message) {
        String sql = "INSERT INTO message(fromemail,toemail,time1,message) "
                + "VALUES(?,?,?,?)";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        try {
            // 关闭自动提交
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,message.getFrom());
            pstmt.setString(2, message.getTo());
            pstmt.setTimestamp(3, message.getTime1() == null ? null
                    : new Timestamp(message.getTime1().getTime()));
            pstmt.setString(4, message.getMessage());

            pstmt.executeUpdate();
            // 事务提交
            conn.commit();
        } catch (SQLException e) {
            try {
                // 事务回滚
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(null, pstmt, conn);
        }
    }
    public MessageList selectByFromTo(String from,String to){
        MessageList messageList=new MessageList();
        String sql = "SELECT * FROM message WHERE fromemail=? and toemail=?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, from);
            pstmt.setString(2, to);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Message message=new Message();
                message.setMessageId(rs.getInt("id"));
                message.setFrom(rs.getString("fromemail"));
                message.setTo(rs.getString("toemail"));
                message.setMessage(rs.getString("message"));
                message.setTime1(rs.getDate("time1"));
                messageList.getMessageListlist().add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(null, pstmt, conn);
        }
        return messageList;
    }
    public List<String> selectByTo(String to){
        List<String> fromList=new LinkedList<String>();
        String sql = "SELECT DISTINCT fromemail FROM message WHERE toemail=?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, to);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                fromList.add(rs.getString("fromemail"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(null, pstmt, conn);
        }
        return fromList;
    }
}
