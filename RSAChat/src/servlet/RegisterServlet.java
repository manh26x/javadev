package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserAccount;
import conn.ConnectionUtils;
import utils.DBUtils;

@WebServlet(urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	public RegisterServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(this.getServletContext().getContextPath()); // go home
		ServletOutputStream out = response.getOutputStream();
		out.println("<script>$(document).ready(function(){$(\"#myModal\").modal(\"show\");});</script>");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String firstname = request.getParameter("firstname");
//		String lastname = request.getParameter("lastname");
//		String gender = request.getParameter("gender");
//		String username = request.getParameter("username");
//		String email = request.getParameter("email");
//		String password = request.getParameter("password");
//		Connection conn;
//		try {
//			conn = ConnectionUtils.getConnection();
//			UserAccount user = DBUtils.findUser(conn, username, password);
//			doGet(request, response);
//			
//			
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		doGet(request, response);
	}
}
