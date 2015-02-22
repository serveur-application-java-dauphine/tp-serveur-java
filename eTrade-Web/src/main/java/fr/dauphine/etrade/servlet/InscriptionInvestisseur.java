package fr.dauphine.etrade.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.dauphine.etrade.Constantes.Constantes;
import fr.dauphine.etrade.api.UserRepository;
import fr.dauphine.etrade.model.User;

/**
 * Servlet implementation class InscriptionInvestisseur
 */
@WebServlet(description = "This servlet deals with the inscription of a new investor.", urlPatterns = { "/InscriptionInvestisseur" })
public class InscriptionInvestisseur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// A voir pour l'ajouter
	@EJB
	private UserRepository ur;
	
	
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			User newUser = new User();
			String userName = request.getParameter(Constantes.I_USER_NAME);
			String mail = request.getParameter(Constantes.I_USER_MAIL);
			String userPassword = request.getParameter(Constantes.I_USER_PASSWORD);
			String userConfirmPassword = request.getParameter(Constantes.I_USER_CONFIRM_PASSWORD);
			
			// On rev�rifie c�t� serveur que le mot de passe soit bien le m�me que le mdp confirm�
			if(userPassword.equals(userConfirmPassword)){
				//Si les mdp sont �gaux, on ins�re l'utilisateur en base.
				InitialContext initialContext = new InitialContext();
				
				newUser.setUserName(userName);
				newUser.setMailAddress(mail);
				newUser.setPassword(userPassword);
				
				UserRepository userRepository = (UserRepository) initialContext.lookup("java:global/eTrade-ejb/ServicesUserBean!fr.dauphine.etrade.api.UserRepository");
			
				System.out.println("userrepository loaded.");
				userRepository.ajouterUser(newUser);
			}
		} catch(NamingException e) {
			e.printStackTrace();
		}
		
	}

}
