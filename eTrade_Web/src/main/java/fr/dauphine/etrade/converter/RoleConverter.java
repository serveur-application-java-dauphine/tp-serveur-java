package fr.dauphine.etrade.converter;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import fr.dauphine.etrade.api.ServicesRole;
import fr.dauphine.etrade.model.Role;

@FacesConverter("fr.dauphine.etrade.converter.RoleConverter")
public class RoleConverter implements Converter {

	@EJB
	private ServicesRole sr;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
		//System.out.println("getAsObject() : "+arg2);
		Role r = new Role();
		long id = Long.getLong(arg2);
		System.out.println(arg2);
		r.setIdRole(id);
		return r;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		System.out.println("getAsString()");
		return arg2.toString();
	}

}
