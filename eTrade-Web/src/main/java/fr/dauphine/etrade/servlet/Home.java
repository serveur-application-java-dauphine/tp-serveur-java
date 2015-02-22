package fr.dauphine.etrade.servlet;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dauphine.etrade.api.UserRepository;
@WebServlet("/Home")
public class Home extends HttpServlet {

	private static final long serialVersionUID = 6575782921956558199L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			UserRepository s = (UserRepository) (new InitialContext()).lookup("ejb:eTrade-webEAR/eTrade-ejb/ServicesUserBean!fr.dauphine.etrade.api.UserRepository");
			s.rechercheUser();
			System.out.println("ok");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
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
