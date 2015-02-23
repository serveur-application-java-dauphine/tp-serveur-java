package fr.dauphine.etrade.servlet;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dauphine.etrade.api.ServicesUser;

@WebServlet("/Home")
public class Home extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			ServicesUser s = InitialContext.doLookup("ejb:eTrade-webEAR/eTrade-ejb/ServicesUserBean!fr.dauphine.services.ServicesUser");
			s.allUsers();
			System.out.println("ok");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		super.doGet(req, resp);
		
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		super.doPost(req, resp);
	}

}
