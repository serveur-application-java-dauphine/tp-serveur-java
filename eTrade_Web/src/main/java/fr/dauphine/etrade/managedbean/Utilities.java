package fr.dauphine.etrade.managedbean;

import java.io.IOException;

import javax.faces.context.FacesContext;

import fr.dauphine.etrade.model.Utilisateur;

public final class Utilities {

	/**
	 * Gets and returns to the developer some informations about a ManagedBean
	 * 
	 * @param managedBeanClasse
	 */
	public final static <T extends Object> T getManagedBean(
			Class<T> managedBeanClasse) {
		FacesContext fc = FacesContext.getCurrentInstance();
		String managedBeanNameString = "#{" + managedBeanClasse.getSimpleName()
				+ "}";
		System.out.println(managedBeanNameString);
		Object obj = fc.getApplication().getELResolver()
				.getValue(fc.getELContext(), null, managedBeanNameString);
		System.out.println(obj);
		@SuppressWarnings("deprecation")
		Object obj1 = fc
				.getApplication()
				.createValueBinding(
						"#{" + managedBeanClasse.getSimpleName() + "}")
				.getValue(fc);
		System.out.println(obj1);
		@SuppressWarnings("deprecation")
		Utilisateur utilisateur = (Utilisateur) fc.getApplication()
				.createValueBinding("#{sessionUserManagedBean.utilisateur}")
				.getValue(fc);
		System.out.println(utilisateur);
		return managedBeanClasse.cast(obj1);
	}

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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
