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
        //设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        //获取请求参数
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String requestPath=request.getParameter("requestPath");
        UserDAO dao=new UserDAO();
        int UserID=dao.login(email,password);
        if(UserID!=0){
            int type=dao.getType(email);
            Users users=new Users(UserID,email,password,type);
            request.getSession().setAttribute("SESSION_USER",users);
            //获取ServletContext对象
            ServletContext context=super.getServletContext();
            //从ServletContext对象获取count属性存储的计数值
            Integer count =(Integer) context.getAttribute("count");
            if(count==null){
                count=1;
            }else{
                count+=1;
            }
            //将更新后的数值存储到ServletContext对象的count属性中
            context.setAttribute("count",count);
            //判断是否已存在请求路径
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

                    // 将简历基本信息存入request对象进行请求转
                    request.setAttribute("basicinfo", basicinfo);
                    request.getRequestDispatcher("user/only.jsp").forward(request,response);
                }else if(type==3){
                    response.sendRedirect("AllServlet?num=1");
                }

            }
        }else{
            //用户登录信息错误,进行错误提示
            out.print("<script type='text/javascript'>");
            out.print("alert('用户名或密码错误，请重新输入!');");
            out.print("window.location='login.jsp';");
            out.print("</script>");
        }
    }
}
