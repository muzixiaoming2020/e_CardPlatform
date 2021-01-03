package jsu.servlet;

import jsu.bean.EmployeeBasicinfo;
import jsu.bean.Message;
import jsu.bean.Users;
import jsu.dao.EmployeeDAO;
import jsu.dao.MessageDAO;
import jsu.dao.UserDAO;

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

@WebServlet("/ManageDepartmentUpdateServlet")
public class ManageDepartmentUpdateServlet extends HttpServlet {
    public ManageDepartmentUpdateServlet() {
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
        // ��ȡ�����������
        String type = request.getParameter("type");
        String email=request.getParameter("email");
        UserDAO userDAO=new UserDAO();
        EmployeeDAO dao = new EmployeeDAO();
        MessageDAO messageDAO=new MessageDAO();
        if("add".equals(type)){
            // ��װ��������
            EmployeeBasicinfo basicinfo = this.requestDataObj(request);
            dao.update(basicinfo);
            Message message=new Message();
            message.setTime1(new Date(System.currentTimeMillis()));
            message.setFrom("12345@guanli.com");
            message.setTo(email);
            message.setMessage("��ϲ���Ϊ"+basicinfo.getDepartment()+"��"+basicinfo.getPosition());
            messageDAO.add(message);
            response.sendRedirect("ManageEmployeeServlet?departmentId=0");
        }else if("delete".equals(type)){ // ��ѯ����
            String deparementRadio=request.getParameter("deparementRadio");
            String deleteRedio=request.getParameter("deleteRedio");
            if(deleteRedio.equals("��")){
                dao.delete(email);
                userDAO.updateType(3,email);
            }else if(deleteRedio.equals("��")&&deparementRadio.equals("��")){
                EmployeeBasicinfo employeeBasicinfo=dao.selectBasicinfoByID(email);
                employeeBasicinfo.setDepartment("");
                employeeBasicinfo.setSalary(0);
                employeeBasicinfo.setPosition("");
                dao.update(employeeBasicinfo);
            }
            response.sendRedirect("ManageEmployeeServlet?departmentId=0");
        }else if("updateSelect".equals(type)){
            EmployeeBasicinfo basicinfo=null;
            basicinfo = this.requestDataObj(request);
            boolean bool=userDAO.isExistEmail(basicinfo.getEmployeeEmail());
            System.out.println(bool);
            if(bool){
                userDAO.updateType(2,email);
                dao.add(basicinfo);
                Message message=new Message();
                message.setTime1(new Date(System.currentTimeMillis()));
                message.setFrom("12345@guanli.com");
                message.setTo(email);
                message.setMessage("��ϲ���Ϊ"+basicinfo.getDepartment()+"��"+basicinfo.getPosition());
                messageDAO.add(message);
                request.getRequestDispatcher("ManageEmployeeServlet?departmentId=0").forward(request, response);
            } else{
                PrintWriter out = response.getWriter();
                out.print("<script type='text/javascript'>");
                out.print("alert('����δ�ҵ���');");
                out.print("window.location='user/manage_department_update.jsp?type=add';");
                out.print("</script>");
            }
        }else if("update".equals(type)){
            EmployeeBasicinfo basicinfo=null;
            basicinfo=dao.selectBasicinfoByID(email);
            request.setAttribute("basicinfo", basicinfo);
            request.getRequestDispatcher("user/manage_department_update.jsp?type=update").forward(request, response);
        }
    }
    /**
     * ����������ݷ�װ��һ������
     *
     * @param request
     * @return
     * @throws
     */
    private EmployeeBasicinfo requestDataObj(HttpServletRequest request) {
        EmployeeBasicinfo basicinfo = null;
        // �����������
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
        // ���������ݷ�װ��һ������������Ϣ����
        basicinfo = new EmployeeBasicinfo(name,email, sex,department
                , position,s, address, phone,birthdayDate
                );
        return basicinfo;
    }
}
