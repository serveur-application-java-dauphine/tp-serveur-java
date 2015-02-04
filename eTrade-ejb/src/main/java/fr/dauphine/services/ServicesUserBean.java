package fr.dauphine.services;

import javax.ejb.Remote;

import fr.dauphine.beans.User;

@Remote(ServicesUser.class)
public class ServicesUserBean implements ServicesUser{

	@Override
	public User rechercheUser() {
		// TODO Auto-generated method stub
		return null;
	}

}
