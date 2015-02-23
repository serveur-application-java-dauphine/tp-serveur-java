package fr.dauphine.etrade.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dauphine.etrade.model.User;
import fr.dauphine.etrade.services.ServicesUser;

/**
 * Servlet implementation class InscriptionInvestisseur
 */
@WebServlet(description = "This servlet deals with the inscription of a new investor.", urlPatterns = { "/InscriptionInvestisseur" })
public class InscriptionInvestisseur extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionInvestisseur() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}
	
	//Usage direct via la no-interface-view
	@EJB
	ServicesUser servicesUser;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ServicesUser s;
		//try {
			//s = InitialContext.doLookup("ejb:eTrade-webEAR/eTrade-ejb/ServicesUserBean!fr.dauphine.etrade.services.ServicesUser");
			User user = new User();
			user.setMail(request.getParameter("mail"));
			user.setName(request.getParameter("name"));
			user.setPassword(request.getParameter("password"));
			servicesUser.addUser(user);
			System.out.println("OK");
		/*} catch (NamingException e) {
			e.printStackTrace();
		}*/
	}

}
