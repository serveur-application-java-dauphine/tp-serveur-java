package fr.dauphine.etrade.managedbean;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;

public final class Utilities {
	
	private static Logger LOG = Logger.getLogger(Utilities.class.getName());
	
	public final static <T extends Object> T getManagedBean(Class<T> managedBeanClasse){
		FacesContext fc = FacesContext.getCurrentInstance();
		String managedBeanNameString = "#{"+managedBeanClasse.getSimpleName()+"}";
		
		Object obj = fc.getApplication().getELResolver().getValue(fc.getELContext(), null, managedBeanNameString);
		if (obj==null){
			LOG.info(managedBeanNameString+" est null et va �tre cr��");
			try {
				fc.getApplication().getELResolver().setValue(fc.getELContext(), null, managedBeanNameString, managedBeanClasse.newInstance());
				obj = fc.getApplication().getELResolver().getValue(fc.getELContext(), null, managedBeanNameString);
			} catch (Exception e) {
				LOG.log(Level.WARNING, managedBeanNameString+" n'a pas pu �tre cr��");
			} 
			
		}

		return managedBeanClasse.cast(obj);
	}
	
	public final static void redirect(String namePage){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(namePage);
			LOG.info("Redirection vers "+namePage);
		} catch (IOException e) {
			LOG.log(Level.WARNING, "Erreur lors de la redirection vers "+namePage);
		}
	}
	
	public final static void showAttribute(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<Object,Object> map = fc.getAttributes();
		for (Entry<Object, Object> set : map.entrySet()){
			System.out.println(set.getKey());
			System.out.println(set.getValue());
		}
	}
	
	public static void returnError(String message){
		//FacesContext.getCurrentInstance().getExternalContext().responseSendError(, message);
	}

}
