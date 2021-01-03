package jsu.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    private int MessageId;
    //发送邮箱
    private String from;
    //接收邮箱
    private String to;
    //发送时间
    private Date time1;
    private String strtime;
    private String message;
    public Message(){}

    public Message(int messageId, String from, String to
            , Date time1, String mesage) {
        MessageId = messageId;
        this.from = from;
        this.to = to;
        this.time1 = time1;
        this.message = message;
    }

    public int getMessageId() {
        return MessageId;
    }

    public void setMessageId(int messageId) {
        MessageId = messageId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getTime1() {
        return time1;
    }

    public void setTime1(Date time1) {
        this.time1 = time1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(time1==null){
            this.strtime="";
        }else{
            this.strtime = sdf.format(time1);
        }
    }

    public String getStrtime() {
        return strtime;
    }

    public void setStrtime(String strtime) {
        this.strtime = strtime;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthdayDate = null;
        try {
            birthdayDate = sdf.parse(strtime);
        } catch (ParseException e) {
            birthdayDate = null;
        }
        this.setTime1(birthdayDate);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String mesage) {
        this.message = mesage;
    }
}
