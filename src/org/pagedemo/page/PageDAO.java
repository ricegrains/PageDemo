package org.pagedemo.page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.pagedemo.db.DBConnection;

public class PageDAO extends DBConnection {

	// 查出所有的记录数
	public int getRows() {
		Connection conn = this.getConnection();
		String sql = "select count(*) from t_page";
		int rows = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				rows = rs.getInt(1);
			}
			System.out.println("rows " + rows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return rows;
	}
}
