package learn.servlet.beans;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/other/RedirectDemo")
public class RedirectDemoServlet extends HttpServlet {

	/**
	 * author: Mike
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String redirect = request.getParameter("redirect");
		System.out.println("redirect: " + redirect);
		if("true".equals(redirect)) {
			// contextPath: Là một String rỗng "" hoặc khác rỗng.
            // Nếu khác rỗng, nó luôn bắt đầu bởi /
            // và không kết thúc bởi /
			String contextPath = request.getContextPath();
			System.out.println("context: " + contextPath);
			// ==> /HelloServlet/showMe
			response.sendRedirect(contextPath + "/showMe");
			return;
		}
		ServletOutputStream out = response.getOutputStream();
        out.println("<h3>Text of RedirectDemoServlet</h3>");
        out.println("- servletPath=" + request.getServletPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
