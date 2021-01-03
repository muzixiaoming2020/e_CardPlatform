package jsu.bean;

public class Users {
    private int usersId;
    private String usersEmail;
    private String usersPassword;
    //权限(管理员，员工，顾客)
    private int usersType;
    public Users(){}
    public Users(int usersId, String usersEmail, String usersPwd, int userstype) {
        this.usersId = usersId;
        this.usersEmail = usersEmail;
        usersPassword = usersPwd;
        usersType = userstype;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public String getUsersEmail() {
        return usersEmail;
    }

    public void setUsersEmail(String usersEmail) {
        this.usersEmail = usersEmail;
    }

    public String getUsersPwd() {
        return usersPassword;
    }

    public void setUsersPwd(String usersPwd) {
        usersPassword = usersPwd;
    }

    public int getUserstype() {
        return usersType;
    }

    public void setUserstype(int userstype) {
        usersType = userstype;
    }

    @Override
    public String toString() {
        return "Users{" +
                "usersId=" + usersId +
                ", usersEmail='" + usersEmail + '\'' +
                ", usersPassword='" + usersPassword + '\'' +
                ", usersType=" + usersType +
                '}';
    }
}
