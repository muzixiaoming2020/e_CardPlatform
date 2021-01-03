package jsu.servlet;

import jsu.bean.Employee;
import jsu.bean.EmployeeBasicinfo;
import jsu.bean.Users;
import jsu.dao.EmployeeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/OnlyBasicinfoServlet")
public class OnlyBasicinfoServlet extends HttpServlet {
    public OnlyBasicinfoServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        // 设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // 获取请求操作类型
        String type = request.getParameter("type");
        if("add".equals(type)){
            // 封装请求数据
            EmployeeBasicinfo basicinfo = this.requestDataObj(request);
            Users users=(Users)request.getSession().getAttribute("SESSION_USER");
            // 向数据库中添加当前员工的数据库
            EmployeeDAO dao = new EmployeeDAO();
            int basicinfoID=dao.selectBasicinfoByID(users.getUsersEmail()).getEmployeeId();
            if(basicinfoID!=0){
                dao.update(basicinfo);
            }else {
                dao.add(basicinfo);
                basicinfoID=dao.selectBasicinfoByID(users.getUsersEmail()).getEmployeeId();
            }
            // 将标识存入会话对象中
            request.getSession().setAttribute("SESSION_RESUMEID", basicinfoID);
            // 操作成功，跳回“个人信息”页面
            response.sendRedirect("OnlyBasicinfoServlet?type=select");
        }else if("select".equals(type)){ // 查询操作
            // 从会话对象中获取当前登录用户标识
            Users applicant = (Users)request.getSession().getAttribute("SESSION_USER");
            // 根据简历标识查询简历基本信息
            EmployeeDAO dao = new EmployeeDAO();
            EmployeeBasicinfo basicinfo = dao.selectBasicinfoByID(applicant.getUsersEmail());
            // 将简历基本信息存入request对象进行请求转
            request.setAttribute("basicinfo", basicinfo);
            request.getRequestDispatcher("user/only.jsp").forward(request, response);
        }else if("updateSelect".equals(type)){ // 更新前的查询
            // 从会话对象中获取当前登录用户标识
            Users applicant = (Users)request.getSession().getAttribute("SESSION_USER");
            // 根据简历标识查询简历基本信息
            EmployeeDAO dao = new EmployeeDAO();
            EmployeeBasicinfo basicinfo = dao.selectBasicinfoByID(applicant.getUsersEmail());
            // 将简历基本信息存入request对象进行请求转发
            request.setAttribute("basicinfo", basicinfo);
            request.getRequestDispatcher("user/onlyBasicinfoUpdate.jsp").forward(request, response);
        }
    }
    /**
     * 将请求的数据封装成一个对象
     *
     * @param request
     * @return
     * @throws
     */
    private EmployeeBasicinfo requestDataObj(HttpServletRequest request) {
        EmployeeBasicinfo basicinfo = null;
        // 获得请求数据
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String sex = request.getParameter("sex");
        String department = request.getParameter("department");
        String position = request.getParameter("position");
        String salary = request.getParameter("salary");
        int s=0;
        if(salary==null){
            s=0;
        }else if(salary.equals("")){
            s=0;
        }else{
            s=Integer.parseInt(salary);
        }
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String birthday = request.getParameter("birthday");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthdayDate = null;
        try {
            birthdayDate = sdf.parse(birthday);
        } catch (ParseException e) {
            birthdayDate = null;
        }
        // 将请求数据封装成一个简历基本信息对象
        basicinfo = new EmployeeBasicinfo(name,email, sex,department
                , position,s, address, phone,birthdayDate
                );
        return basicinfo;
    }
}
