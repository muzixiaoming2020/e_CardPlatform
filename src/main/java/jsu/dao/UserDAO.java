package jsu.dao;

import jsu.util.DBUtil;

import java.sql.*;
import java.util.Date;

public class UserDAO {
    //��֤email�Ƿ��ѱ�ע��
    public boolean isExistEmail(String email){
        Connection conn= DBUtil.getConnection();
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        String sql="SELECT * FROM user WHERE email=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            if (rs.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(rs, pstmt, conn);
        }
        return false;
    }

    /**
     * ��ȡ��¼Ȩ��
     *
     */
    public int getType(String email){
        Connection conn = DBUtil.getConnection();
        String sql = "select type from user where email='"+email+"'";
        Statement s = null;
        int type= 0;
        try {
            s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                type= rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.closeJDBC(null, s, conn);
        }
        return type;
    }

    public void updateType(int type,String email){
        Connection conn = DBUtil.getConnection();
        String sql = "update user set type=? where email=?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, type);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(null, pstmt, conn);
        }
    }
    /**
     * ��������ж���������
     *
     */
    public int getSum(){
        Connection conn = DBUtil.getConnection();
        String sql = "select count(*) from user";
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

    /**
     * ��ְ����Ϣע�ᱣ��
     *
     * @param email
     * @param password
     */
    public void save(String email, String password) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO user(email,password,type) VALUES(?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            pstmt.setInt(3, 3);//��ʼ��Ȩ��Ϊ�˿�
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(null, pstmt, conn);
        }
    }



    /**
     * ע���û���¼
     *
     * @param email
     * @param password
     * @return
     */
    public int login(String email, String password) {
        int UserID = 0;
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT id FROM user WHERE email='"+email+"' and password='"+password+"'";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next())
                UserID = rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(rs, pstmt, conn);
        }
        return UserID;
    }
}