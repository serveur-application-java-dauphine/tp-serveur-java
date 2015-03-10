package fr.dauphine.etrade.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dauphine.etrade.Constantes.ConstantesForms;
import fr.dauphine.etrade.api.ServicesUtilisateur;
import fr.dauphine.etrade.model.Role;
import fr.dauphine.etrade.model.Utilisateur;

/**
 * Servlet implementation class InscriptionInvestisseur
 */
@WebServlet(description = "This servlet deals with the inscription of a new administrator.", urlPatterns = { "/InscriptionInvestisseur" })
public class InscriptionAdministrateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Usage direct via la no-interface-view
	@EJB
	private ServicesUtilisateur servicesUtilisateur;
		
       
    /**
     * @see HttpServlet#HttpServlet()
     */
   public InscriptionAdministrateur() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(testRequest(request)){
			Utilisateur Utilisateur = new Utilisateur();
			Utilisateur.setMail(request.getParameter(ConstantesForms.I_USER_MAIL));
			Utilisateur.setName(request.getParameter(ConstantesForms.I_USER_NAME));
			//Mot de passe en clair
			Utilisateur.setPassword(request.getParameter(ConstantesForms.I_USER_PASSWORD)); 
			
			Role role = new Role();
			//Role Administrateur -> en dur c'est nul
			role.setIdRole((long)1);
		
			Utilisateur.setRole(role);
			
			servicesUtilisateur.addUtilisateur(Utilisateur);			
			
			//Message de confirmation
			response.getWriter().append("New administrator added");
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
		boolean result = true;
		result = request.getParameter(ConstantesForms.I_USER_PASSWORD).equals(request.getParameter(ConstantesForms.I_USER_CONFIRM_PASSWORD));
		 //&& request.getParameter(Constantes.I_USER_PASSWORD).length() >= Constantes.C_PASSWORD_MIN_SIZE;
		return result;
	}
	

}
