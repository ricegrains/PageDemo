package org.pagedemo.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.pagedemo.db.DBConnection;
import org.pagedemo.page.PageDAO;
import org.pagedemo.page.PageUtil;
import org.pagedemo.po.User;

/**
 * @author qpy_2006 这里主要讲解分页的使用。 所以这个对数据库的操作的dao只提供查找功能。 其他功能如感兴趣可自行完善
 */
public class UserDAO extends DBConnection {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 构造方法,初始化数据库连接操作
	public UserDAO() {
		conn = this.getConnection();
	}

	// 根据分页的请求类别查找某一页的数据
	// 分页的请求类别有：first、next、previous、last
	// 第一次访问index.jsp页面查找出第一页
	public List<User> findAll(PageUtil pageBean) {		
		// 查询语句，各参数的含义：为从第几行读取数据；第二个是这一次读取多少行记录;
		// 默认从第0行开始读取
		// 按照stu_num 升序排列
		String sql = "select * from t_page order by stu_num limit ?,?";
		List<User> users = new ArrayList<User>();
		try {
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, pageBean.getFirstRow());
			pstmt.setInt(2, pageBean.getMaxSize());
			this.rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setStu_num(rs.getString("stu_num"));
				user.setUsername(rs.getString("username"));
				user.setAge(rs.getString("age"));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 最后关闭数据库的连接conn对象
			// 其他的比如pstmt、rs会随着conn的关闭自动关闭
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return users;
	}
}
