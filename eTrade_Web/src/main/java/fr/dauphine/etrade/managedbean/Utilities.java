package fr.dauphine.etrade.managedbean;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import fr.dauphine.etrade.api.Response;
import fr.dauphine.etrade.api.ResponseError;

public final class Utilities {

	private static Logger LOG = Logger.getLogger(Utilities.class.getName());

	/**
	 * Usefull method to redirect the user to another web page
	 * 
	 * @param namePage
	 *            the page the user will be redirected
	 */
	public final static void redirect(String namePage) {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(namePage);
			LOG.info("Redirection vers " + namePage);
		} catch (IOException e) {
			LOG.log(Level.WARNING, "Erreur lors de la redirection vers "
					+ namePage);
		}
	}

	/**
	 * Returns an error message to the interface
	 * 
	 * @param message
	 */
	public static boolean responseIsError(Response response) {
		if (response instanceof ResponseError){
			addError(((ResponseError)response).error, null);
			return true;
		}
		return false;
	}
	
	public static void addError(String summary, String detail){
		addMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
	}
	
	
	public static void addMessage(Severity severity, String summary, String detail){
		FacesMessage message = new FacesMessage(severity, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
