package fr.dauphine.etrade.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import fr.dauphine.etrade.api.ServicesRole;
import fr.dauphine.etrade.model.Role;

@ManagedBean
@RequestScoped
public class RoleManagedBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private List<Role> roles;
	
	@EJB
	private ServicesRole sr;

	public RoleManagedBean() {
		
	}


	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		if (roles==null)
			roles = sr.getRoles();
		return roles;
	}


	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
