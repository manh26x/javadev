package learn.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InitParamServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String emailSupport1;
	public InitParamServlet() {
		
	}
	
	// Phương thức này luôn được gọi 1 lần 
	// Ngay sau khi đối tượng Servlet được gọi ra
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
        // Lấy ra giá trị của tham số khởi tạo (initialization parameter) của Servlet.
        // (Theo Cấu hình của Servlet này trong web.xml).
		this.emailSupport1 = config.getInitParameter("emailSupport1");
	}
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        // Lấy ra giá trị của tham số khởi tạo (initialization parameter) theo một cách
        // khác.
        String emailSupport2 = this.getServletConfig().getInitParameter("emailSupport2");
 
        ServletOutputStream out = response.getOutputStream();
 
        out.println("<html>");
        out.println("<head><title>Init Param</title></head>");
 
        out.println("<body>");
        out.println("<h3>Init Param</h3>");
        out.println("<p>emailSupport1 = " + this.emailSupport1 + "</p>");
        out.println("<p>emailSupport2 = " + emailSupport2 + "</p>");
        out.println("</body>");
        out.println("<html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
	
}
