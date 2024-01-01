package hello.servlet.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import hello.servlet.domain.HelloData;

/**
 * http://localhost:8080/response-json
 *
 */
@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Content-Type: application/json
		response.setHeader("content-type", "application/json");
		response.setCharacterEncoding("utf-8");
		HelloData data = new HelloData();
		data.setUsername("han");
		data.setAge(1994);
		// {"username":"kim","age":20}
		
		String result = objectMapper.writeValueAsString(data);	//역직렬화, 스프링 사용시  return data; 하면 알아서 역직렬화 됨
		response.getWriter().write(result);
	}
}