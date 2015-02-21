package fr.dauphine.etrade.services;

import javax.ejb.Remote;

import fr.dauphine.etrade.beans.User;

@Remote(ServicesUser.class)
public class ServicesUserBean implements ServicesUser{

	@Override
	public User rechercheUser() {
		return null;
	}

}
