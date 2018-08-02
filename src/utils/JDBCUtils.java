package utils;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.http.HttpServlet;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JDBCUtils extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	private JDBCUtils() {}
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	
	static 
	{
		try
		{
			Properties props = new Properties();
			Reader is = new FileReader("src/db.properties");
			props.load(is);
			driver = props.getProperty("driverClass");
			url = props.getProperty("url");
			user = props.getProperty("user");
			password = props.getProperty("password");
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		
	}
	
	public static Connection getConnection()
	{
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (Exception e) {
			throw new RuntimeException(e + "连接失败");
		}
	}
	
	public static void close(Connection con, Statement stmt)
	{
		if (stmt != null) {
			try {
				stmt.close();
			}
			catch (SQLException e)
			{ }
		}
		if (con != null) {
			try {
				con.close();
			}
			catch (SQLException e)
			{ }
		}
	}
	
	public static void close(Connection con, Statement stmt, ResultSet res)
	{
		if (res != null) {
			try {
				res.close();
			}
			catch (SQLException e)
			{ }
		}
		if (stmt != null) {
			try {
				stmt.close();
			}
			catch (SQLException e)
			{}
		}
		if (con != null) {
			try {
				con.close();
			}
			catch (SQLException e)
			{ }
		}
	}
}
