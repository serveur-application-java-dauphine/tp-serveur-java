package fr.dauphine.etrade.services;

import javax.ejb.Remote;

import fr.dauphine.etrade.beans.User;

@Remote
public interface ServicesUser {
	
	User rechercheUser();

}
