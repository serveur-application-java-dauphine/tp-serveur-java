package fr.dauphine.etrade.services;

import java.util.ArrayList;

import javax.ejb.Remote;

import fr.dauphine.etrade.beans.User;

@Remote
public interface ServicesUser {
	Boolean addUser(User user);
	Boolean delUser(User user);
	ArrayList<User> allUsers();
	Boolean updateUser(User user);
}
