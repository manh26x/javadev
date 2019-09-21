package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserAccount;
import utils.MyUtils;

@WebServlet(urlPatterns = {"/userInfo"})
public class UserInfoServlet extends HttpServlet {

	/**
	 * author: Mike
	 */
	private static final long serialVersionUID = 1L;
	public UserInfoServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		// check User has logged on
		UserAccount loginedUser = MyUtils.getLoginedUser(session);
		
		// If not logined
		if(loginedUser == null) {
			// redirect to login page
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		// store into the request attribute before forwarding
		request.setAttribute("user", loginedUser);
		
		// if User has logged on, forward to /WEB-INF/views/userInfoView.jsp
		RequestDispatcher dispatcher //
			= this.getServletContext().getRequestDispatcher("/WEB-INF/views/userInfoView.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
	
}
