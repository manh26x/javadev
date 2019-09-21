package learn.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		ServletOutputStream out = res.getOutputStream();
        
	    out.println("<html>");
	    out.println("<head><title>Hello Servlet</title></head>");
	        
	    out.println("<body>");
	    out.println("<h3>Hello World</h3>");
	    out.println("This is my first Servlet");
	    out.println("</body>");
	    out.println("<html>");
	}
	
	   protected void doPost(HttpServletRequest request,
	           HttpServletResponse response) throws ServletException, IOException {
	       this.doGet(request, response);
	   }
	 

}
