package fr.dauphine.etrade.managedbean;

import java.io.Serializable;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
@ManagedBean
@ApplicationScoped
public class ApplicationManagedBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\." +
			"[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
			"(\\.[A-Za-z]{2,})$";


	/**
	 * @return the eMAIL_PATTERN
	 */
	public String getEMAIL_PATTERN() {
		return EMAIL_PATTERN;
	}

}