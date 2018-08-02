package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils {
	
	private static DataSource dataSource = new ComboPooledDataSource();
	private static ThreadLocal<Connection> t1 = new ThreadLocal<Connection>();
	
	//直接获取连接池
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	//获取连接对象
	public static Connection getConnection() throws SQLException {
		Connection con = t1.get();
		if (con ==  null) {
			con = dataSource.getConnection();
			t1.set(con);
		}
		return con;
	}
	
	//开启事务
	public static void startTransaction() throws SQLException {
		Connection con = getConnection();
		if (con != null) {
			con.setAutoCommit(false);
		}
	}
	
	//事务回滚
	public static void rollback() throws SQLException {
		Connection con = getConnection();
		if (con != null) {
			con.rollback();
		}
	}
	
	//提交并且关闭资源即从ThreadLocal中释放
	public static void commitAndRelease() throws SQLException {
		Connection con = getConnection();
		if (con != null) {
			con.commit();
			con.close();
			t1.remove();
		}
	}
	
	//关闭资源
	public static void closeConnection() throws SQLException {
		Connection con = getConnection();
		if (con != null) {
			con.close();
		}
	}
	
	public static void closeStatement(Statement st) throws SQLException {
		if (st != null) {
			st.close();
		}
	}
	
	public static void closeResultSet(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
	}
}
