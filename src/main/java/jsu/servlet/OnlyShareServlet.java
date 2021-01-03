package jsu.servlet;

import jsu.bean.EmployeeBasicinfo;
import jsu.bean.Users;
import jsu.dao.EmployeeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/OnlyShareServlet")
public class OnlyShareServlet extends HttpServlet {
    public OnlyShareServlet() {
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
        String email = request.getParameter("email").replace('_','@');
        EmployeeDAO dao = new EmployeeDAO();
        EmployeeBasicinfo basicinfo = dao.selectBasicinfoByID(email);
        // 将简历基本信息存入request对象进行请求转
        request.setAttribute("basicinfo", basicinfo);
        request.getRequestDispatcher("user/onlyShare.jsp").forward(request, response);

    }

}
