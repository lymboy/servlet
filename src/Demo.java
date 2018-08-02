import java.sql.Connection;

import utils.JDBCUtils;

public class Demo {
	public static void main(String[] args) {
		Connection con = JDBCUtils.getConnection();
		if (con != null) {
			System.out.println(con);
		}
		else {
			System.out.println("Connecting Error...");
		}
	}
	
}
