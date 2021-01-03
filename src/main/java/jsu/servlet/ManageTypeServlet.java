package jsu.servlet;

import jsu.bean.Power;
import jsu.dao.PowerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/ManageTypeServlet")
public class ManageTypeServlet extends HttpServlet {
    public ManageTypeServlet() {
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
        Power power=null;
        PowerDAO powerDAO=new PowerDAO();
        Map<String,Integer> map=null;
            map=new HashMap<String, Integer>();
            powerDAO.update("name",Integer.parseInt(request.getParameter("name")));
            powerDAO.update("email",Integer.parseInt(request.getParameter("email")));
            powerDAO.update("sex",Integer.parseInt(request.getParameter("sex")));
            powerDAO.update("department",Integer.parseInt(request.getParameter("department")));
            powerDAO.update("position",Integer.parseInt(request.getParameter("position")));
            powerDAO.update("salary",Integer.parseInt(request.getParameter("salary")));
            powerDAO.update("address",Integer.parseInt(request.getParameter("address")));
            powerDAO.update("phone",Integer.parseInt(request.getParameter("phone")));
            powerDAO.update("photo",Integer.parseInt(request.getParameter("photo")));
            powerDAO.update("birthday",Integer.parseInt(request.getParameter("birthday")));
        response.sendRedirect("user/manage_power.jsp");
    }
}
