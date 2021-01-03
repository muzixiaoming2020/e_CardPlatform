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
       WEB应用初始化时，容器调用此方法
    */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //获取ServletContext对象
        ServletContext context= servletContextEvent.getServletContext();
        String filepath=context.getRealPath("/")+"/count.txt";
        System.out.println(filepath);
        File f = new File(filepath);
        if (!f.exists())
            f.mkdirs();
        //从文件中读取计数器的数值
        BufferedReader reader=new BufferedReader(new InputStreamReader(context.getResourceAsStream("/count.txt")));
        String strcount= null;
        try {
            strcount = reader.readLine();
            if(strcount==null || "".equals(strcount))
                strcount="0";
            int count=Integer.parseInt(strcount);
            reader.close();
            //把计数器对象保存到web应用范围
            context.setAttribute("count",count);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    /*
       WEB应用停止时，容器调用此方法
    */
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //获取ServletContext对象
        ServletContext context= servletContextEvent.getServletContext();
        //从web应用范围获得计数器对象
        Integer counter=(Integer) context.getAttribute("count");
        if(counter!=null){
            //把计数器的数值写到项目发布目录下的count.txt文件中
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
