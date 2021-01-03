package jsu.dao;

import jsu.bean.Department;
import jsu.bean.EmployeeBasicinfo;
import jsu.util.DBUtil;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DepartmentDAO {
    public int getSum(){
        Connection conn = DBUtil.getConnection();
        String sql = "select count(*) from department";
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
    public void delete(Department department){
        String sql = "DELETE FROM department where name='"+department.getDepartmentName()+"'";
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
    public void add(Department department) {
        String sql = "INSERT INTO department(name)"
                + "VALUES(?)";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, department.getDepartmentName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(null, pstmt, conn);
        }
    }
    public String selectDepartmentById(int id){
        String sql = "SELECT * FROM department where id=?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        Department department=new Department();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                department.setDepartmentName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(null, pstmt, conn);
        }
        return department.getDepartmentName();
    }
    public List<Department> selectDepartment(){
        List<Department> List=new LinkedList<Department>();
        String sql = "SELECT * FROM department";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Department department=new Department();
                department.setDepartmentId(rs.getInt("id"));
                department.setDepartmentName(rs.getString("name"));
                List.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(null, pstmt, conn);
        }
        return List;
    }
    public void update(String newname,String oldname) {
        String sql = "UPDATE department "
                + "SET name=? WHERE name=?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newname);
            pstmt.setString(2, oldname);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeJDBC(null, pstmt, conn);
        }
    }
}
