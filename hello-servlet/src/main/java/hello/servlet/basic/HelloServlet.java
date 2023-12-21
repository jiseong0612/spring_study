package hello.servlet.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override	// post, get 둘다 받아서 처리하는 메소드, doGet, doPost보다 우선순위가 높다.
	 protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("HelloServlet.service");
		System.out.println("request = " + request);
		System.out.println("response = " + response);
		String username = request.getParameter("username");
		System.out.println("username = " + username);
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write("hello " + username);
	}
}

