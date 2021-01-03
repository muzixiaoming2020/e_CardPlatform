package jsu.bean;

import jsu.dao.EmployeeDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeBasicinfo {
    //员工编号
    private int EmployeeId;
    //员工姓名
    private String EmployeeName;
    //员工邮箱
    private String EmployeeEmail;
    //性别
    private String sex;
    //部门
    private String department;
    //职位
    private String position;
    //工资
    private int salary;
    //地址
    private String address;
    //电话
    private String phone;
    //头像
    private String photo;
    //权限
    private Power power;
    private Date birthday;
    //生日的字符串
    private String strbirthday;
    // 信息更新结果
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
        // 更新简历基本信息
        try {
            EmployeeDAO dao = new EmployeeDAO();
            dao.update(employeeBasicinfo);
        } catch (Exception e) {
            UpdateResult = "更新失败！";
        }
        UpdateResult = "更新成功！";
    }
}
