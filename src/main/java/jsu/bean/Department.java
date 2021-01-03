package jsu.bean;

public class Department {
    //部门编号
    private int DepartmentId;
    //部门名
    private String DepartmentName;
    public Department(){}

    public Department(int departmentId, String departmentName) {
        DepartmentId = departmentId;
        DepartmentName = departmentName;
    }

    public int getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(int departmentId) {
        DepartmentId = departmentId;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }
}
