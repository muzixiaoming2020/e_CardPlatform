package jsu.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.*;
import java.net.MalformedURLException;

@WebListener
public class VisitCountListener implements ServletContextListener {
    public VisitCountListener(){}
    /*
       WEBӦ�ó�ʼ��ʱ���������ô˷���
    */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //��ȡServletContext����
        ServletContext context= servletContextEvent.getServletContext();
        String filepath=context.getRealPath("/")+"/count.txt";
        System.out.println(filepath);
        File f = new File(filepath);
        if (!f.exists())
            f.mkdirs();
        //���ļ��ж�ȡ����������ֵ
        BufferedReader reader=new BufferedReader(new InputStreamReader(context.getResourceAsStream("/count.txt")));
        String strcount= null;
        try {
            strcount = reader.readLine();
            if(strcount==null || "".equals(strcount))
                strcount="0";
            int count=Integer.parseInt(strcount);
            reader.close();
            //�Ѽ��������󱣴浽webӦ�÷�Χ
            context.setAttribute("count",count);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    /*
       WEBӦ��ֹͣʱ���������ô˷���
    */
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //��ȡServletContext����
        ServletContext context= servletContextEvent.getServletContext();
        //��webӦ�÷�Χ��ü���������
        Integer counter=(Integer) context.getAttribute("count");
        if(counter!=null){
            //�Ѽ���������ֵд����Ŀ����Ŀ¼�µ�count.txt�ļ���
            String filepath=context.getRealPath("/")+"/count.txt";
            File f = new File(filepath);
            if (!f.exists())
                f.mkdirs();
            try {
                PrintWriter pw=new PrintWriter(filepath);
                pw.println(counter.intValue());
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
