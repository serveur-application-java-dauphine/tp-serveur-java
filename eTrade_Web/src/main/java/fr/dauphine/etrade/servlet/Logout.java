package fr.dauphine.etrade.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache, no-store");  
        response.setHeader("Pragma", "no-cache");  
        response.setHeader("Expires", new Date().toString());  
       
        HttpSession session = request.getSession(false);  
		  
        PrintWriter out=response.getWriter();  
        out.print("<p style='float:right'>You are successfully logged out!</p><br/><br/>");  
        System.out.println("--- Before logout ---");  
        System.out.println("Session ID is " + (request.isRequestedSessionIdValid() ? "valid" : "invalid"));    
        request.getRequestDispatcher("index.xhtml").include(request, response);  

        if (session != null)  
        {  
            session.invalidate();  
            System.out.println("Invalidate has been called");  
        }  
        request.logout(); 
        
        System.out.println("--- After logout ---");  
        System.out.println("Session ID is " + (request.isRequestedSessionIdValid() ? "valid" : "invalid"));  
         

        
        out.close();  
        response.sendRedirect("/index.xhtml");  
	}
	
	 public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException    
     {    
          String  value = "no-cache";    
          ((HttpServletResponse)response).setHeader("Cache-Control", value);    
          chain.doFilter(request, response);  
     } 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
