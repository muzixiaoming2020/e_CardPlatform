package jsu.servlet;

import jsu.bean.Employee;
import jsu.bean.EmployeeBasicinfo;
import jsu.bean.MessageList;
import jsu.bean.Users;
import jsu.dao.EmployeeDAO;
import jsu.dao.MessageDAO;

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
import java.util.List;

@WebServlet("/OnlyEmailServlet")
public class OnlyEmailServlet extends HttpServlet {
    public OnlyEmailServlet() {
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
        if("add".equals(type)){
            // ��װ��������
            EmployeeBasicinfo basicinfo = this.requestDataObj(request);
            Users users=(Users)request.getSession().getAttribute("SESSION_USER");
            // �����ݿ�����ӵ�ǰԱ�������ݿ�
            EmployeeDAO dao = new EmployeeDAO();
            int basicinfoID=dao.selectBasicinfoByID(users.getUsersEmail()).getEmployeeId();
            if(basicinfoID!=0){
                dao.update(basicinfo);
            }else {
                dao.add(basicinfo);
                basicinfoID=dao.selectBasicinfoByID(users.getUsersEmail()).getEmployeeId();
            }
            // ����ʶ����Ự������
            request.getSession().setAttribute("SESSION_RESUMEID", basicinfoID);
            // �����ɹ������ء�������Ϣ��ҳ��
            response.sendRedirect("OnlyBasicinfoServlet?type=select");
        }else if("select".equals(type)){ // ��ѯ����
            // ��ȡ�����������
            String email = request.getParameter("email").replace('_','@');
            MessageDAO messageDAO=new MessageDAO();
            List<String> fromList=messageDAO.selectByTo(email);
            MessageList messageList=null;
            if(fromList==null){

            }
            else messageList=messageDAO.selectByFromTo(fromList.get(0),email);
            // ������������Ϣ����request�����������ת
            request.setAttribute("messageList", messageList);
            request.getRequestDispatcher("user/onlyEmail.jsp").forward(request, response);
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
