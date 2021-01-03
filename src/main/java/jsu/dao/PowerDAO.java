package jsu.dao;

import jsu.bean.EmployeeBasicinfo;
import jsu.bean.Message;
import jsu.bean.MessageList;
import jsu.bean.Power;
import jsu.util.DBUtil;

import java.sql.*;

public class PowerDAO {
    public Power selectPower(){
        Power power=new Power();
        String sql = "SELECT * FROM power";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                String name=rs.getString("name");
                int type=rs.getInt("type");
                power.getMap().put(name,type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(null, pstmt, conn);
        }
        return power;
    }
    public void update(String name,int type) {
        String sql = "update power "
                + "SET type="+type+ " where name='"+name+"'";
        System.out.println(sql);
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(null, pstmt, conn);
        }
    }
}
