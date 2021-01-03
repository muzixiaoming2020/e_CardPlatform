package jsu.servlet;

import jsu.dao.UserDAO;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * ע�Ṧ��ʵ��
 *
 *
 *
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    public RegisterServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        // �����������Ӧ����
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        // ��ȡ�������
        String type = request.getParameter("type");
        String email = request.getParameter("email");
        // �ж��Ƿ���ʹ��Ajax�������emailΨһ����֤
        if("emailAjaxValidate".equals(type)){
            UserDAO dao = new UserDAO();
            boolean flag = dao.isExistEmail(email);
            if(flag)
                out.print("�����ѱ�ע�ᣡ");
            else
                out.print("�������ʹ�ã�");
        }else{
            String password = request.getParameter("password");
                // �ж������Ƿ��ѱ�ע��
                UserDAO dao = new UserDAO();
                boolean flag = dao.isExistEmail(email);
                if(flag){
                    // ������ע��,���д�����ʾ
                    out.print("<script type='text/javascript'>");
                    out.print("alert('�����ѱ�ע�ᣬ���������룡');");
                    out.print("window.location='register.jsp';");
                    out.print("</script>");
                }else{
                    // ����δ��ע�ᣬ����ע���û���Ϣ
                    dao.save(email,password);
                    // ע��ɹ����ض��򵽵�¼ҳ��
                    response.sendRedirect("login.jsp");
                }
            }
        }


}
