package fr.dauphine.etrade.servlet;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dauphine.etrade.api.ServicesUtilisateur;
import fr.dauphine.etrade.model.Role;
import fr.dauphine.etrade.model.Utilisateur;
import fr.dauphine.etrade.Constantes.ConstantesForms;
import fr.dauphine.etrade.Constantes.PatternsControl;

/**
 * Servlet implementation class InscriptionInvestisseur
 */
@WebServlet(description = "This servlet deals with the registration of a new investor.", urlPatterns = { "/Login" })
public class Login extends HttpServlet {
	
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	//Usage direct via la no-interface-view
	@EJB
	private ServicesUtilisateur servicesUtilisateur;
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response); //405
	}
	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(testRequest(request)){
			String email = request.getParameter(ConstantesForms.I_USER_MAIL);
			String password = request.getParameter(ConstantesForms.I_USER_PASSWORD);
			System.out.println(email);
			System.out.println(password);
			Utilisateur user = servicesUtilisateur.getUtilisateurLogin(email,password);			
			
			//Message de confirmation
			if (user==null){
				response.getWriter().append("Login KO");
				return;
			}
			
		} else {
			System.out.println("Test Request KO");
		}

	}
	
	/**
	 * This method tests if all obligatory inputs are completed
	 * @param request
	 * @return
	 */
	private boolean testRequest(HttpServletRequest request) {
		/*if ( /*request.getParameter(ConstantesForms.I_USER_PASSWORD).length() < ConstantesForms.C_PASSWORD_MIN_SIZE
				|| !Pattern.matches(PatternsControl.EMAIL_PATTERN, request.getParameter(ConstantesForms.I_USER_MAIL))
			)
			return false;*/
		
		return true;
	}
}