package fr.dauphine.etrade.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dauphine.etrade.api.ServicesUtilisateur;
import fr.dauphine.etrade.constantes.ConstantesForms;
import fr.dauphine.etrade.model.Role;
import fr.dauphine.etrade.model.Utilisateur;

/**
 * Servlet implementation class InscriptionInvestisseur
 */
@WebServlet(description = "This servlet deals with the inscription of a new investor.", urlPatterns = { "/Inscription" })
public class Inscription extends HttpServlet {
	
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
			Utilisateur Utilisateur = new Utilisateur();
			Utilisateur.setEmail(request.getParameter(ConstantesForms.I_USER_EMAIL));
			Utilisateur.setFirstname(request.getParameter(ConstantesForms.I_USER_FIRSTNAME));
			Utilisateur.setLastname(request.getParameter(ConstantesForms.I_USER_LASTNAME));
			Utilisateur.setPassword(request.getParameter(ConstantesForms.I_USER_PASSWORD)); 
			Utilisateur.setAdress(request.getParameter(ConstantesForms.I_USER_ADDRESS));
			Utilisateur.setZipcode(request.getParameter(ConstantesForms.I_USER_ZIP_CODE));
			Utilisateur.setCity(request.getParameter(ConstantesForms.I_USER_CITY));

			Utilisateur.setBirthdate(java.sql.Date.valueOf(request.getParameter(ConstantesForms.I_USER_BIRTH_DATE)));
			
			Role role = new Role();
			role.setIdRole(Long.parseLong(request.getParameter(ConstantesForms.I_USER_ASKED_ROLE)));
			
			
			Utilisateur.setRole(role);
			
			// L'utilisateur n'a pas encore �t� valid� au moment de son insersion en base
			Utilisateur.setValidRole(false);
			
			servicesUtilisateur.addUtilisateur(Utilisateur);			
			
			//Message de confirmation
			System.out.println("Inscription OK");
			response.getWriter().append("Inscription OK");
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
		if ( request.getParameter(ConstantesForms.I_USER_PASSWORD).length() < ConstantesForms.C_PASSWORD_MIN_SIZE
				|| !request.getParameter(ConstantesForms.I_USER_PASSWORD).equals(request.getParameter(ConstantesForms.I_USER_CONFIRM_PASSWORD))
				//|| !Pattern.matches(Appli.EMAIL_PATTERN, request.getParameter(ConstantesForms.I_USER_EMAIL))
			)
			return false;
		
		return true;
	}
}