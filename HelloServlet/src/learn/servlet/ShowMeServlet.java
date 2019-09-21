package learn.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import learn.servlet.beans.Constants;;

@WebServlet("/showMe")
public class ShowMeServlet extends HttpServlet{
	
	 private static final long serialVersionUID = 1L;
	 
	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	 
	        // Lấy giá trị trong 1 thuộc tính (attribute) của request.
	        String value = (String) request.getAttribute(Constants.ATTRIBUTE_USER_NAME_KEY);
	 
	        ServletOutputStream out = response.getOutputStream();
	 
	        out.println("<h1>ShowMeServlet</h1>");
	        out.println(value);
	    }
	 
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        this.doGet(request, response);
	    }
}
