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
        //����û��Ự��ʹ��ʧЧ
        request.getSession().invalidate();
        //��ȡServletContext����
        ServletContext context=super.getServletContext();
        //��ServletContext�����ȡcount���Դ洢�ļ���ֵ
        Integer count =(Integer) context.getAttribute("count");
        if(count==0){
        }else{
            count-=1;
        }
        //�����º����ֵ�洢��ServletContext�����count������
        context.setAttribute("count",count);
        //�����ض�����վ��ҳ
        response.sendRedirect("login.jsp");
    }
}
