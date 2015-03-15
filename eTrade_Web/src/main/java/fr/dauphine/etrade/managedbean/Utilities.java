package fr.dauphine.etrade.managedbean;

import java.io.IOException;

import javax.faces.context.FacesContext;

public final class Utilities {

	public final static <T extends Object> T getManagedBean(Class<T> managedBeanClasse){
		FacesContext fc = FacesContext.getCurrentInstance();
		String managedBeanNameString = "#{"+managedBeanClasse.getSimpleName()+"}";
		System.out.println(managedBeanNameString);
		Object obj = fc.getApplication().getELResolver().getValue(fc.getELContext(), null, managedBeanNameString);
		System.out.println(obj);
		//Object obj = fc.getApplication().createValueBinding("#{"+managedBeanClasse.getSimpleName()+"}").getValue(fc);
		return managedBeanClasse.cast(obj);
	}
	
	public final static void redirect(String namePage){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(namePage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
