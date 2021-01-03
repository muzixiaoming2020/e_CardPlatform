package jsu.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ����Ȩ�޹�����
 * 
 * @author
 *
 */
@WebFilter(
		urlPatterns = { "/user/*" },
		servletNames = {"jsu.servlet.OnlyBasicinfoServlet",
		"jsu.servlet.OnlyPicUploadServlet",
		"jsu.servlet.OnlyShareServlet" },
		initParams = { @WebInitParam(name = "loginPage", value = "login.jsp") },
		dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD })
public class Filter implements javax.servlet.Filter {

	private String loginPage = "login.jsp";

	public Filter() {

	}

	public void init(FilterConfig fConfig) throws ServletException {
		// ��ȡ����������ʱת���ҳ��
		loginPage = fConfig.getInitParameter("loginPage");
		if (null == loginPage) {
			loginPage = "login.jsp";
		}
	}
	public void destroy() {
		this.loginPage = null;
	}
	public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		// �жϱ����ص������û��Ƿ��ڵ�¼״̬
		if (session.getAttribute("SESSION_USER") == null) {
			// ��ȡ�����ص������ַ������
			String requestPath = req.getRequestURI();
			if (req.getQueryString() != null) {
				requestPath += "?" + req.getQueryString();
			}
			// �������ַ���浽request������ת������¼ҳ��
			req.setAttribute("requestPath", requestPath);
			request.getRequestDispatcher( "/" + loginPage)
					.forward(request, response);
			return;
		} else {
			chain.doFilter(request, response);
		}
	}

}
