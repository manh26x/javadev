package learn.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import learn.servlet.beans.*;

@WebServlet(urlPatterns = { "/userInfo"})
public class UserInfoServlet extends HttpServlet {

	/**
	 * author Mike
	 */
	private static final long serialVersionUID = 1L;
	
	public UserInfoServlet() {
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ServletOutputStream out = response.getOutputStream();
		
		// Lấy ra đối tượng HttpSession.
		HttpSession session = request.getSession();
		
		// Lấy ra đối tượng UserInfo đã được lưu vào session
        // sau khi người dùng login thành công.
		UserInfo loginedInfo = (UserInfo) session.getAttribute(Constants.SESSION_USER_KEY);
		
		 // Chưa login, Redirect (chuyển hướng) về trang login (LoginServlet).
		if(loginedInfo == null) {
			// ==> /helloTutorial/login
			response.sendRedirect(this.getServletContext().getContextPath() + "/login");
			return;
		}
		
		out.println("<html>");
        out.println("<head><title>Session example</title></head>");
 
        out.println("<body>");
 
        out.println("<h3>User Info:</h3>");
 
        out.println("<p>User Name:" + loginedInfo.getUserName() + "</p>");
        out.println("<p>Country:" + loginedInfo.getCountry() + "</p>");
        out.println("<p>Post:" + loginedInfo.getPost() + "</p>");
 
        out.println("</body>");
        out.println("<html>");
	}

}
