package cn.chat.wechat.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;




public class DBUtil {
	private static String driverClass;
	private static String url;
	private static String username;
	private static String password;
	
	static{
		
		ResourceBundle config = ResourceBundle.getBundle("config");
		driverClass = config.getString("driverClass");
		url = config.getString("url");
		username = config.getString("username");
		username = config.getString("password");
		
		//加载驱动
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 创建连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url,username,username);
	}
	/**
	 * 关闭资源
	 * @param rs
	 * @param stamt
	 * @param conn
	 */
	public static void closeAll(ResultSet rs,Statement stamt,Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stamt!=null){
			try {
				stamt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
