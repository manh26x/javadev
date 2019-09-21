package learn.ServletFilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Log2Filter implements Filter {

	private String logFile;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Log2Filter is destroy!");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		if(this.logFile != null) {
			// Ghi thông tin log vào file
			this.logToFile(this.logFile);
		}
		
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		this.logFile = arg0.getInitParameter("logFile");
		
		System.out.println("Log File: " + logFile);
	}
	
	private void logToFile(String fileName) {
        // Ghi log vào file..
        System.out.println("Write log to file " + fileName);
    }
	
}
