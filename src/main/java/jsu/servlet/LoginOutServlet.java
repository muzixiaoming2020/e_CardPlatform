package jsu.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginOutServlet")
public class LoginOutServlet extends HttpServlet {
    public LoginOutServlet(){
        super();
    }
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)throws ServletException, IOException{
        this.doPost(request, response);
    }
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)throws ServletException,IOException{
        //获得用户会话，使其失效
        request.getSession().invalidate();
        //获取ServletContext对象
        ServletContext context=super.getServletContext();
        //从ServletContext对象获取count属性存储的计数值
        Integer count =(Integer) context.getAttribute("count");
        if(count==0){
        }else{
            count-=1;
        }
        //将更新后的数值存储到ServletContext对象的count属性中
        context.setAttribute("count",count);
        //请求重定向到网站首页
        response.sendRedirect("login.jsp");
    }
}
