package jsu.dao;

import jsu.bean.*;
import jsu.util.DBUtil;

import java.sql.*;
import java.util.*;

public class EmployeeDAO {
	public void delete(String email){
		String sql = "DELETE FROM employee where email'"+email+"'";
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
    public int getSum(){
        Connection conn = DBUtil.getConnection();
        String sql = "select count(*) from employee";
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
	 * 基本信息添加
	 * @param employeeBasicinfo
	 *
	 * @return
	 */
	public void add(EmployeeBasicinfo employeeBasicinfo) {
		String sql = "INSERT INTO employee(name,email" +
				", sex, department,position,salary,address" +
				",phone,photo, birthday) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			// 关闭自动提交
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, employeeBasicinfo.getEmployeeName());
			pstmt.setString(2, employeeBasicinfo.getEmployeeEmail());
			pstmt.setString(3, employeeBasicinfo.getSex());
			pstmt.setString(4, employeeBasicinfo.getDepartment());
			pstmt.setString(5, employeeBasicinfo.getPosition());
			pstmt.setInt(6, employeeBasicinfo.getSalary());
			pstmt.setString(7, employeeBasicinfo.getAddress());
			pstmt.setString(8, employeeBasicinfo.getPhone());
			pstmt.setString(9, employeeBasicinfo.getPhoto());
			pstmt.setTimestamp(10, employeeBasicinfo.getBirthday() == null ? null
					: new Timestamp(employeeBasicinfo.getBirthday().getTime()));
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

	/**
	 * 照片更新
	 * 
	 * @param email
	 * @param newFileName
	 */
	public void updateHeadShot(String email, String newFileName) {
		String sql = "UPDATE employee SET photo=? WHERE email=?";
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newFileName);
			pstmt.setString(2, email);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
	}
	public void updateDepartment(String newname, String oldname) {
		String sql = "UPDATE employee SET department=? WHERE department=?";
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
	/**
	 * 根据标识查询简历基本信息
	 * @param
	 * @return
	 */
	public EmployeeBasicinfo selectBasicinfoByID(String email){
		EmployeeBasicinfo resume = new EmployeeBasicinfo();
		String sql = "SELECT * FROM employee WHERE email=?";
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				resume.setEmployeeId(rs.getInt("id"));
				resume.setEmployeeName(rs.getString("name"));
				resume.setEmployeeEmail(rs.getString("email"));
				resume.setSex(rs.getString("sex"));
				resume.setDepartment(rs.getString("department"));
				resume.setPosition(rs.getString("position"));
				resume.setSalary(rs.getInt("salary"));
				resume.setAddress(rs.getString("address"));
				resume.setPhone(rs.getString("phone"));
				resume.setPhoto(rs.getString("photo"));
				resume.setBirthday(rs.getDate("birthday"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
		return resume;
	}

	public List<EmployeeBasicinfo> selectBasicinfoByDepartment(Department department){
		List<EmployeeBasicinfo> list=new LinkedList<EmployeeBasicinfo>();
		String sql=null;
		if(department.getDepartmentName()!=null){
			sql = "SELECT * FROM employee WHERE department='"+department.getDepartmentName()+"'";
		}else {
			sql = "SELECT * FROM employee WHERE department is null";
		}
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				EmployeeBasicinfo resume = new EmployeeBasicinfo();
				resume.setEmployeeId(rs.getInt("id"));
				resume.setEmployeeName(rs.getString("name"));
				resume.setEmployeeEmail(rs.getString("email"));
				resume.setSex(rs.getString("sex"));
				resume.setDepartment(rs.getString("department"));
				resume.setPosition(rs.getString("position"));
				resume.setSalary(rs.getInt("salary"));
				resume.setAddress(rs.getString("address"));
				resume.setPhone(rs.getString("phone"));
				resume.setPhoto(rs.getString("photo"));
				resume.setBirthday(rs.getDate("birthday"));
				list.add(resume);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
		return list;
	}

	/**
	 * 更新基本信息
	 * @param basicinfo
	 */
	public void update(EmployeeBasicinfo basicinfo) {
		String sql = "UPDATE employee "
				+ "SET name=?,email=?,sex=?,department=?,"
				+ "position=?,salary=?,address=?,phone=?,birthday=? "
				+ "WHERE email=?";
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, basicinfo.getEmployeeName());
			pstmt.setString(2, basicinfo.getEmployeeEmail());
			pstmt.setString(3, basicinfo.getSex());
			pstmt.setString(4, basicinfo.getDepartment());
			pstmt.setString(5, basicinfo.getPosition());
			pstmt.setInt(6, basicinfo.getSalary());
			pstmt.setString(7, basicinfo.getAddress());
			pstmt.setString(8, basicinfo.getPhone());
			pstmt.setTimestamp(9, basicinfo.getBirthday() == null ? null
					: new Timestamp(basicinfo.getBirthday().getTime()));
			pstmt.setString(10, basicinfo.getEmployeeEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
	}

}
