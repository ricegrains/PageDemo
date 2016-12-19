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
 * @author qpy_2006 Servlet,本系统的核心控制器
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
		// 获得HttpSession并取得session，用户保存数据
		// 以便在jsp页面或者其他servlet中获得该session中得值
		HttpSession session = request.getSession();
		// 获取分页的请求类别
		String pageType = request.getParameter("pageType");
		String action = request.getParameter("action");
		List<User> users = new ArrayList<User>();
		PageDAO pd = new PageDAO();
		PageUtil pu = new PageUtil();
		UserDAO ud = new UserDAO();
		// 获取数据库中记录数
		int rows = pd.getRows();
		if ("initial".equals(action)) {
			// 第一次进入index.jsp页面时，查询出第一页
			// 设置分页的各个属性，参见PageUtil这个类
			pu.counter("first", rows);
			session.setAttribute("pageBean", pu);
		} else if ("page".equals(action)) {
			// 点击分页请求时执行下一代码
			pu = (PageUtil) session.getAttribute("pageBean");
			pu.counter(pageType, pu.getTotalRows());
		}		
		users = ud.findAll(pu);
		// 把查询的结果users放到session中，
		// 以便在jsp页面从session读取该users对象
		session.setAttribute("users", users);

		// 把PageUtil放到session中
		session.setAttribute("pageBean", pu);
		// 在servlet跳转到某个jsp页面
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	public void init() throws ServletException {

	}

}
