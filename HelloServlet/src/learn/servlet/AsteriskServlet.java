package learn.servlet;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/any/*"})
public class AsteriskServlet extends HttpServlet {

	
	private static final long serialVersionUID = 2275585325224745258L;
	public AsteriskServlet() {
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletOutputStream out = response.getOutputStream();
		
		out.println("<html>");
		out.println("<head><title>Asterisk</title></head>");
		out.println("<body>");
		out.println("<h3>Hi, your URL match /any/*</h3>");
		out.println("</body>");
		out.println("</html>");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.doGet(request, response);
	}
}
