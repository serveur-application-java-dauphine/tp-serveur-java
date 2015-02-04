package fr.dauphine.services;

import javax.ejb.Remote;

import fr.dauphine.beans.User;

@Remote
public interface ServicesUser {
	
	User rechercheUser();

}
