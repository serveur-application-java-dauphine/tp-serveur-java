package fr.dauphine.etrade.services;


import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import fr.dauphine.etrade.api.ServicesRole;
import fr.dauphine.etrade.model.Role;
import fr.dauphine.etrade.persit.Connexion;

@Remote(ServicesRole.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesRoleBean implements ServicesRole{

	@Override
	public List<Role> getRoles() {
		String query = "from Role";
		return Connexion.getInstance().queryListResult(query, Role.class);
	}

	@Override
	public Role getRole(int id) {
		return Connexion.getInstance().find(Role.class, id);
	}
	
}
