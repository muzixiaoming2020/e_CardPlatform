package jsu.servlet;

import jsu.bean.Department;
import jsu.bean.EmployeeBasicinfo;
import jsu.bean.Users;
import jsu.dao.DepartmentDAO;
import jsu.dao.EmployeeDAO;
import jsu.dao.UserDAO;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    public LoginServlet(){super();}
    protected void doGet(HttpServletRequest request
            ,HttpServletResponse response)throws ServletException, IOException {
        this.doPost(request,response);
    }
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)throws ServletException, IOException{
        //�����������Ӧ����
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        //��ȡ�������
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String requestPath=request.getParameter("requestPath");
        UserDAO dao=new UserDAO();
        int UserID=dao.login(email,password);
        if(UserID!=0){
            int type=dao.getType(email);
            Users users=new Users(UserID,email,password,type);
            request.getSession().setAttribute("SESSION_USER",users);
            //��ȡServletContext����
            ServletContext context=super.getServletContext();
            //��ServletContext�����ȡcount���Դ洢�ļ���ֵ
            Integer count =(Integer) context.getAttribute("count");
            if(count==null){
                count=1;
            }else{
                count+=1;
            }
            //�����º����ֵ�洢��ServletContext�����count������
            context.setAttribute("count",count);
            //�ж��Ƿ��Ѵ�������·��
            if(!"".equals(requestPath)&&requestPath!=null){
                response.sendRedirect(requestPath);
            }else{
                request.getSession().setAttribute("usertype",type);
                if(type==1){
                    DepartmentDAO departmentDAO=new DepartmentDAO();
                    List<Department> departmentList=null;
                    departmentList=departmentDAO.selectDepartment();
                    request.getSession().setAttribute("departmentList",departmentList);
                    response.sendRedirect("user/manage_department.jsp");
                }else if(type==2){
                    EmployeeDAO edao = new EmployeeDAO();
                    EmployeeBasicinfo basicinfo = edao.selectBasicinfoByID(email);
                    if(basicinfo.getEmployeeEmail()!=null){

                    }else {
                        basicinfo.setEmployeeEmail(email);
                        edao.add(basicinfo);
                    }

                    // ������������Ϣ����request�����������ת
                    request.setAttribute("basicinfo", basicinfo);
                    request.getRequestDispatcher("user/only.jsp").forward(request,response);
                }else if(type==3){
                    response.sendRedirect("AllServlet?num=1");
                }

            }
        }else{
            //�û���¼��Ϣ����,���д�����ʾ
            out.print("<script type='text/javascript'>");
            out.print("alert('�û����������������������!');");
            out.print("window.location='login.jsp';");
            out.print("</script>");
        }
    }
}
