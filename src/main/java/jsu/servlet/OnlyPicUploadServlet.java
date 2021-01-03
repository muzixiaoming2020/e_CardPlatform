package jsu.servlet;

import jsu.bean.Employee;
import jsu.bean.Users;
import jsu.dao.EmployeeDAO;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

/**
 * 头像图片上传
 * 
 * @author QST青软实训
 *
 */
@WebServlet("/OnlyPicUploadServlet")
@MultipartConfig
public class OnlyPicUploadServlet extends HttpServlet {

	public OnlyPicUploadServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取上传文件域
		Part part = request.getPart("photo");
		/*
		// 获取上传文件名称
		part.getSubmittedFileName();
		String fileName = part.getSubmittedFileName();

		 */
		//上传文件名字
		String cd = part.getHeader("Content-Disposition");
		//截取不同类型的文件需要自行判断
		String fileName = cd.substring(cd.lastIndexOf("=")+2, cd.length()-1);
		// 为防止上传文件重名，对文件进行重命名
		String newFileName = System.currentTimeMillis()
				+ fileName.substring(fileName.lastIndexOf("."));
		// 将上传的文件保存在服务器项目发布路径的applicant/images目录下
		String filepath = getServletContext().getRealPath("/user/images");

		File f = new File(filepath);
		if (!f.exists())
			f.mkdirs();
		part.write(filepath + "/" + newFileName);
		// 从会话对象中获取当前用户简历标识
		Users users=(Users)request.getSession().getAttribute("SESSION_USER");
		// 更新简历照片
		EmployeeDAO dao = new EmployeeDAO();
		dao.updateHeadShot(users.getUsersEmail(), newFileName);
		// 照片更新成功，回到“我的简历”页面
		response.sendRedirect("OnlyBasicinfoServlet?type=select");
	}

}
