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

import fr.dauphine.etrade.Constantes.Constantes;
import fr.dauphine.etrade.api.IUser;
import fr.dauphine.etrade.model.User;
import fr.dauphine.etrade.service.UserRepository;

/**
 * Servlet implementation class InscriptionInvestisseur
 */
@WebServlet(description = "This servlet deals with the inscription of a new investor.", urlPatterns = { "/InscriptionInvestisseur" })
public class InscriptionInvestisseur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// A voir pour l'ajouter
	@EJB
	private IUser u;
	
	
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
			
			// On revérifie côté serveur que le mot de passe soit bien le même que le mdp confirmé
			if(userPassword.equals(userConfirmPassword)){
				//Si les mdp sont égaux, on insère l'utilisateur en base.
				//InitialContext initialContext = new InitialContext();
				
				newUser.setUserName(userName);
				newUser.setMailAddress(mail);
				newUser.setPassword(userPassword);
				
				//IUser user = (IUser) initialContext.lookup("java:global/eTrade-ear/eTrade-ejb/UserRepository!fr.dauphine.etrade.service.UserRepository");
			
				System.out.println("userrepository loaded.");
				u.ajouterUser(newUser);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
