package fr.dauphine.etrade.managedbean;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class ApplicationManagedBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//Doit etre equivalent au code de la table role!!!!!
	private final String ROLE_CODE_ADMINISTRATEUR = "Administrateur";
	private final String ROLE_CODE_SOCIETE = "Societe";
	private final String ROLE_CODE_INVESTISSEUR = "Investisseur";
	
	private final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
	private final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\." +
			"[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
			"(\\.[A-Za-z]{2,})$";


	private final String DATE_PATTERN = "dd/MM/yyyy";
	
	/**
	 * @return the eMAIL_PATTERN
	 */
	public String getEMAIL_PATTERN() {
		return EMAIL_PATTERN;
	}

	/**
	 * @return the dATE_PATTERN
	 */
	public String getDATE_PATTERN() {
		return DATE_PATTERN;
	}

	/**
	 * @return the rOLE_CODE_ADMINISTRATEUR
	 */
	public String getROLE_CODE_ADMINISTRATEUR() {
		return ROLE_CODE_ADMINISTRATEUR;
	}

	/**
	 * @return the rOLE_CODE_SOCIETE
	 */
	public String getROLE_CODE_SOCIETE() {
		return ROLE_CODE_SOCIETE;
	}

	/**
	 * @return the rOLE_CODE_INVESTISSEUR
	 */
	public String getROLE_CODE_INVESTISSEUR() {
		return ROLE_CODE_INVESTISSEUR;
	}

	/**
	 * @return the pASSWORD_PATTERN
	 */
	public String getPASSWORD_PATTERN() {
		return PASSWORD_PATTERN;
	}

}
