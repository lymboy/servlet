package login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import utils.JDBCUtils;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		int count = 0;
		this.getServletContext().setAttribute("count", count);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//从请求中获得用户名和密码
		String username = req.getParameter("user");
		String pwd = req.getParameter("password");
		
		//从数据库中验证该用户名和密码是否正确
		QueryRunner runner = new QueryRunner();
		Connection con = JDBCUtils.getConnection();
		String sql = "SELECT * FROM loginuser where id=? and name=?";
		List<Object[]> list = null;
		try {
			list = runner.query(con, sql, new ArrayListHandler(), username, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//根据返回的结果给用户不同显示信息
		if (list != null) {
			ServletContext context = this.getServletContext();
			Integer count = (Integer) context.getAttribute("count");
			count++;
			resp.getWriter().write(count);
			context.setAttribute("count", count);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
}
