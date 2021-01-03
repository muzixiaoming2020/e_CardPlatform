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

@WebServlet("/ManageEmployeeServlet")
public class ManageEmployeeServlet extends HttpServlet {
    public ManageEmployeeServlet() {super();
    }

    protected void doGet(HttpServletRequest request
            , HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request
            , HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        DepartmentDAO departmentDAO=new DepartmentDAO();
        EmployeeDAO employeeDAO=new EmployeeDAO();
        List<Department> departmentList=null;
        departmentList=departmentDAO.selectDepartment();
        List<EmployeeBasicinfo> employeeBasicinfoList=null;
        Department department=department=new Department();
        String id=request.getParameter("departmentId");
        if(id.equals("0")) {
            department.setDepartmentName(null);
            if(departmentList!=null)
                employeeBasicinfoList=employeeDAO.selectBasicinfoByDepartment(department);
            department.setDepartmentName("");
            List<EmployeeBasicinfo> elist=null;
            if(departmentList!=null)
                elist=employeeDAO.selectBasicinfoByDepartment(department);
            for(int i=0;i<elist.size();i++){
                employeeBasicinfoList.add(elist.get(i));
            }
        }else{
            department.setDepartmentId(Integer.parseInt(id));
            department.setDepartmentName(departmentDAO.selectDepartmentById(department.getDepartmentId()));
            if(departmentList!=null)
                employeeBasicinfoList=employeeDAO.selectBasicinfoByDepartment(department);
        }

        request.getSession().setAttribute("employeeBasicinfoList",employeeBasicinfoList);
        request.getSession().setAttribute("departmentList",departmentList);
        response.sendRedirect("user/manage_employee.jsp");
    }
}
