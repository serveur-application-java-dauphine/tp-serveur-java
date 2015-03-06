package fr.dauphine.etrade.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dauphine.etrade.api.ServicesUtilisateur;
import fr.dauphine.etrade.model.Role;
import fr.dauphine.etrade.model.Utilisateur;
import fr.dauphine.etrade.Constantes.Constantes;

/**
 * Servlet implementation class InscriptionInvestisseur
 */
@WebServlet(description = "This servlet deals with the inscription of a new investor.", urlPatterns = { "/Inscription" })
public class Inscription extends HttpServlet {
	
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private ServicesUtilisateur ur;
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}
	
	//Usage direct via la no-interface-view
	@EJB
	ServicesUtilisateur servicesUtilisateur;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if(testRequest(request)){
			Utilisateur Utilisateur = new Utilisateur();
			
	
			Utilisateur.setMail(request.getParameter(Constantes.I_USER_MAIL));
			Utilisateur.setName(request.getParameter(Constantes.I_USER_NAME));
			Utilisateur.setPassword(request.getParameter(Constantes.I_USER_PASSWORD)); 
			Utilisateur.setAdress(request.getParameter(Constantes.I_USER_ADDRESS));
			Utilisateur.setZipcode(request.getParameter(Constantes.I_USER_ZIP_CODE));
			Utilisateur.setCity(request.getParameter(Constantes.I_USER_CITY));
			Utilisateur.setBirthdate(java.sql.Date.valueOf(request.getParameter(Constantes.I_USER_BIRTH_DATE)));
			
			Role role = new Role();
			role.setIdRole(Long.getLong(request.getParameter(Constantes.I_USER_ASKED_ROLE)));
			
			Utilisateur.setRole(role);
			
			// L'utilisateur n'a pas encore été validé au moment de son insersion en base
			Utilisateur.setValidRole(false);
			
			servicesUtilisateur.addUtilisateur(Utilisateur);			
			
			//Message de confirmation
			System.out.println("Inscription OK");
			response.getWriter().append("Inscription OK");
		} else {
			System.out.println("Test Request KO");
		}

	}

	private boolean testRequest(HttpServletRequest request) {
		boolean result = true;
		result = request.getParameter(Constantes.I_USER_PASSWORD).equals(request.getParameter(Constantes.I_USER_CONFIRM_PASSWORD));
		 //&& request.getParameter(Constantes.I_USER_PASSWORD).length() >= Constantes.C_PASSWORD_MIN_SIZE;
		return result;
	}
}