package jsu.servlet;
import jsu.bean.Department;
import jsu.dao.DepartmentDAO;
import jsu.dao.EmployeeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ManageDepartmentServlet")
public class ManageDepartmentServlet extends HttpServlet {
    public ManageDepartmentServlet(){super();}
    protected void doGet(HttpServletRequest request
            ,HttpServletResponse response)throws ServletException,IOException{
        this.doPost(request,response);
    }

    protected  void doPost(HttpServletRequest request
            ,HttpServletResponse response)throws ServletException,IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String type=request.getParameter("type");
        String oldname=request.getParameter("old");
        String newname=request.getParameter("new");
        DepartmentDAO departmentDAO=new DepartmentDAO();
        EmployeeDAO employeeDAO=new EmployeeDAO();
        Department department=new Department();
        List<Department> departmentList=null;
        if(type.equals("update")){
            departmentDAO.update(newname,oldname);
            employeeDAO.updateDepartment(newname,oldname);
        }else if(type.equals("add")){
            department.setDepartmentName(newname);
            departmentDAO.add(department);
        }else if(type.equals("delete")){
            department.setDepartmentName(oldname);
            employeeDAO.updateDepartment(null,oldname);
            departmentDAO.delete(department);
        }
        departmentList=departmentDAO.selectDepartment();
        request.getSession().setAttribute("departmentList",departmentList);
        response.sendRedirect("user/manage_department.jsp");
    }
}
