package fr.dauphine.etrade.managedbean;

import javax.faces.context.FacesContext;

public final class Utilities {

	public final static <T extends Object> T getOtherManagedBean(Class<T> managedBeanClasse){
		FacesContext fc = FacesContext.getCurrentInstance();
		String managedBeanNameString = "#{"+managedBeanClasse.getSimpleName()+"}";
		System.out.println(managedBeanNameString);
		Object obj = fc.getApplication().getELResolver().getValue(fc.getELContext(), null, managedBeanNameString);
		System.out.println(obj);
		//Object obj = fc.getApplication().createValueBinding("#{"+managedBeanClasse.getSimpleName()+"}").getValue(fc);
		return managedBeanClasse.cast(obj);
	}

}
