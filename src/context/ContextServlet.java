package context;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ContextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获得ServletContext对象
		ServletContext context = this.getServletContext();
		//获得web.xml当中的初始化参数
		String initParameter = context.getInitParameter("driver");
		System.out.println(initParameter);
		//获得a b c d.txt的绝对路径
		//获得a.txt
		String realPath_A = context.getRealPath("a.txt");
		System.out.println(realPath_A);
		//获得b.txt
		String realPath_B = context.getRealPath("WEB_INF/b.txt");
		System.out.println(realPath_B);
		//获得c.txt
		String realPath_C = context.getRealPath("WEB_INF/classes/c.txt");
		System.out.println(realPath_C);
		//获得d.txt------获取不到
		
		
		//getResource()参数是一个相对地址 相对classes
		String path = ContextServlet.class.getClassLoader().getResource("c.txt").getPath();
		System.out.println(path);
		
		
		
		resp.getWriter().write("<h1 style="+"text-align:center;"+">Hello World</h1>");
	}
	
}
