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
 * @author qpy_2006 ������Ҫ�����ҳ��ʹ�á� ������������ݿ�Ĳ�����daoֻ�ṩ���ҹ��ܡ� �������������Ȥ����������
 */
public class UserDAO extends DBConnection {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	ResultSet rs = null;

	// ���췽��,��ʼ�����ݿ����Ӳ���
	public UserDAO() {
		conn = this.getConnection();
	}

	// ���ݷ�ҳ������������ĳһҳ������
	// ��ҳ����������У�first��next��previous��last
	// ��һ�η���index.jspҳ����ҳ���һҳ
	public List<User> findAll(PageUtil pageBean) {		
		// ��ѯ��䣬�������ĺ��壺Ϊ�ӵڼ��ж�ȡ���ݣ��ڶ�������һ�ζ�ȡ�����м�¼;
		// Ĭ�ϴӵ�0�п�ʼ��ȡ
		// ����stu_num ��������
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
			// ���ر����ݿ������conn����
			// �����ı���pstmt��rs������conn�Ĺر��Զ��ر�
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
