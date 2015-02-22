package fr.dauphine.etrade.api;

import fr.dauphine.etrade.model.User;

public interface UserRepository {
	User rechercheUser();
	void ajouterUser(User u);
}
