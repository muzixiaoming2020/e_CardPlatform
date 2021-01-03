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
 * ͷ��ͼƬ�ϴ�
 * 
 * @author QST����ʵѵ
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
		// ��ȡ�ϴ��ļ���
		Part part = request.getPart("photo");
		/*
		// ��ȡ�ϴ��ļ�����
		part.getSubmittedFileName();
		String fileName = part.getSubmittedFileName();

		 */
		//�ϴ��ļ�����
		String cd = part.getHeader("Content-Disposition");
		//��ȡ��ͬ���͵��ļ���Ҫ�����ж�
		String fileName = cd.substring(cd.lastIndexOf("=")+2, cd.length()-1);
		// Ϊ��ֹ�ϴ��ļ����������ļ�����������
		String newFileName = System.currentTimeMillis()
				+ fileName.substring(fileName.lastIndexOf("."));
		// ���ϴ����ļ������ڷ�������Ŀ����·����applicant/imagesĿ¼��
		String filepath = getServletContext().getRealPath("/user/images");

		File f = new File(filepath);
		if (!f.exists())
			f.mkdirs();
		part.write(filepath + "/" + newFileName);
		// �ӻỰ�����л�ȡ��ǰ�û�������ʶ
		Users users=(Users)request.getSession().getAttribute("SESSION_USER");
		// ���¼�����Ƭ
		EmployeeDAO dao = new EmployeeDAO();
		dao.updateHeadShot(users.getUsersEmail(), newFileName);
		// ��Ƭ���³ɹ����ص����ҵļ�����ҳ��
		response.sendRedirect("OnlyBasicinfoServlet?type=select");
	}

}
