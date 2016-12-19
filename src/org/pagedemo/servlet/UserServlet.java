package org.pagedemo.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.pagedemo.page.PageDAO;
import org.pagedemo.page.PageUtil;
import org.pagedemo.po.User;
import org.pagedemo.user.dao.UserDAO;

/**
 * @author qpy_2006 Servlet,��ϵͳ�ĺ��Ŀ�����
 */
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void destroy() {
		super.destroy();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		// ���HttpSession��ȡ��session���û���������
		// �Ա���jspҳ���������servlet�л�ø�session�е�ֵ
		HttpSession session = request.getSession();
		// ��ȡ��ҳ���������
		String pageType = request.getParameter("pageType");
		String action = request.getParameter("action");
		List<User> users = new ArrayList<User>();
		PageDAO pd = new PageDAO();
		PageUtil pu = new PageUtil();
		UserDAO ud = new UserDAO();
		// ��ȡ���ݿ��м�¼��
		int rows = pd.getRows();
		if ("initial".equals(action)) {
			// ��һ�ν���index.jspҳ��ʱ����ѯ����һҳ
			// ���÷�ҳ�ĸ������ԣ��μ�PageUtil�����
			pu.counter("first", rows);
			session.setAttribute("pageBean", pu);
		} else if ("page".equals(action)) {
			// �����ҳ����ʱִ����һ����
			pu = (PageUtil) session.getAttribute("pageBean");
			pu.counter(pageType, pu.getTotalRows());
		}		
		users = ud.findAll(pu);
		// �Ѳ�ѯ�Ľ��users�ŵ�session�У�
		// �Ա���jspҳ���session��ȡ��users����
		session.setAttribute("users", users);

		// ��PageUtil�ŵ�session��
		session.setAttribute("pageBean", pu);
		// ��servlet��ת��ĳ��jspҳ��
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	public void init() throws ServletException {

	}

}
