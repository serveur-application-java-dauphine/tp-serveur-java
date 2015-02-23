package fr.dauphine.etrade.api;

import java.util.ArrayList;

import javax.ejb.Remote;

import fr.dauphine.etrade.model.User;

@Remote
public interface ServicesUser {
	Boolean addUser(User user);
	Boolean delUser(User user);
	ArrayList<User> allUsers();
	Boolean updateUser(User user);
}
