package jsu.bean;

import jsu.dao.EmployeeDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeBasicinfo {
    //Ա�����
    private int EmployeeId;
    //Ա������
    private String EmployeeName;
    //Ա������
    private String EmployeeEmail;
    //�Ա�
    private String sex;
    //����
    private String department;
    //ְλ
    private String position;
    //����
    private int salary;
    //��ַ
    private String address;
    //�绰
    private String phone;
    //ͷ��
    private String photo;
    //Ȩ��
    private Power power;
    private Date birthday;
    //���յ��ַ���
    private String strbirthday;
    // ��Ϣ���½��
    private String UpdateResult;
    public EmployeeBasicinfo(){}

    public EmployeeBasicinfo( String employeeName,
                             String employeeEmail, String sex,
                             String department, String position,
                             int salary, String address,
                             String phone,  Date birthday) {
        EmployeeName = employeeName;
        EmployeeEmail = employeeEmail;
        this.sex = sex;
        this.department = department;
        this.position = position;
        this.salary = salary;
        this.address = address;
        this.phone = phone;
        this.birthday = birthday;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return EmployeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        EmployeeEmail = employeeEmail;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(birthday==null){
            this.strbirthday="";
        }else{
            this.strbirthday = sdf.format(birthday);
        }
    }

    public String getStrbirthday() {
        return strbirthday;
    }

    public void setStrbirthday(String strbirthday) {
        this.strbirthday = strbirthday;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthdayDate = null;
        try {
            birthdayDate = sdf.parse(strbirthday);
        } catch (ParseException e) {
            birthdayDate = null;
        }
        this.setBirthday(birthdayDate);
    }

    public String getUpdateResult() {
        return UpdateResult;
    }

    public void setResumeUpdate(EmployeeBasicinfo employeeBasicinfo) {
        // ���¼���������Ϣ
        try {
            EmployeeDAO dao = new EmployeeDAO();
            dao.update(employeeBasicinfo);
        } catch (Exception e) {
            UpdateResult = "����ʧ�ܣ�";
        }
        UpdateResult = "���³ɹ���";
    }
}
