package fr.dauphine.etrade.services;

import java.util.ArrayList;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import fr.dauphine.etrade.beans.User;

@Remote(ServicesUser.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesUserBean implements ServicesUser{

	@Override
	public Boolean addUser(User user) {
		return Connexion.getInstance().persist(user);
	}

	@Override
	public Boolean delUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<User> allUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
