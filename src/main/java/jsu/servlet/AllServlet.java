package jsu.servlet;

import jsu.bean.Department;
import jsu.bean.EmployeeBasicinfo;
import jsu.dao.DepartmentDAO;
import jsu.dao.EmployeeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AllServlet")
public class AllServlet extends HttpServlet {
    public AllServlet() {super();
    }

    protected void doGet(HttpServletRequest request
            , HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request
            , HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String num=request.getParameter("num");
        DepartmentDAO departmentDAO=new DepartmentDAO();
        EmployeeDAO employeeDAO=new EmployeeDAO();
        List<Department> departmentList=null;
        departmentList=departmentDAO.selectDepartment();
        List<EmployeeBasicinfo> employeeBasicinfoList=null;
        Department department=null;
        if(num.equals("1")){
            department=departmentList.get(0);
        }else if (num.equals("2")){
            department=new Department();
            department.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));
            department.setDepartmentName(departmentDAO.selectDepartmentById(department.getDepartmentId()));
        }
        if(departmentList!=null)
            employeeBasicinfoList=employeeDAO.selectBasicinfoByDepartment(department);
        request.getSession().setAttribute("employeeBasicinfoList",employeeBasicinfoList);
        request.getSession().setAttribute("departmentList",departmentList);
        response.sendRedirect("user/all.jsp");
    }
}
