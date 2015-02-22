package fr.dauphine.etrade.api;

import fr.dauphine.etrade.model.User;

public interface IUser {
	User rechercheUser();
	void ajouterUser(User u);
}
