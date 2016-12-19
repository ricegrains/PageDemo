package org.pagedemo.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.pagedemo.po.User;

/**
 * @author qpy_2006
 * 
 */
public class DBConnection {

	private String driverClassName;
	private String username;
	private String password;
	private String url;
	private Connection conn = null;

	public DBConnection() {
		String fileName = "/DBConnect.properties";
		this.driverClassName = new String(this.getPropertyValue(fileName,
				"driverClassName"));
		this.url = this.getPropertyValue(fileName, "url");
		this.username = this.getPropertyValue(fileName, "username");
		this.password = this.getPropertyValue(fileName, "password");
		try {
			Class.forName(this.driverClassName);
			this.conn = DriverManager.getConnection(this.url, this.username,
					this.password);
		} catch (ClassNotFoundException e) {
			System.err.println("加载驱动程序出错！！" + e.getMessage());
		} catch (SQLException e) {
			System.err.println("获取连接失败！！！" + e.getMessage());
		}
	}

	public Connection getConnection() {
		return this.conn;
	}

	public String getPropertyValue(String fileName, String propName) {
		String propertyValue = new String("");
		InputStream inStream = getClass().getResourceAsStream(fileName);
		Properties props = new Properties();
		try {
			props.load(inStream);
			propertyValue = props.getProperty(propName);
			if (propertyValue == null)
				propertyValue = "";
		} catch (IOException e) {
			System.err.println("读取数据库配置文件失败！！！" + e.getMessage());
			propertyValue = null;
		}
		return propertyValue;
	}
	
	// 测试数据库的连接是否成功...	
	public static void main(String[] args)
	{
		DBConnection db = new DBConnection();
		Connection conn = db.getConnection();
		String sql = "select * from t_page";
		PreparedStatement pstmt;
		List<User> users = new ArrayList<User>();
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
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
		}finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	
		if(users.size()<1)
		{
			System.out.println("没有找到您要找的记录!");
		}else
		{
			for(User u:users)
			{
				System.out.println("学生学号: "+u.getStu_num());
				System.out.println("学生姓名: "+u.getUsername());
				System.out.println("~~~~~~~~~~~~~~~");
			}
		}
	}
}
