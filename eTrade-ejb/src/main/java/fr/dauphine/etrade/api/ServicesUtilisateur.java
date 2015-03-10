package fr.dauphine.etrade.api;

import java.util.List;

import javax.ejb.Remote;

import fr.dauphine.etrade.model.Utilisateur;

@Remote
public interface ServicesUtilisateur {
	Utilisateur addUtilisateur(Utilisateur Utilisateur);
	Utilisateur delUtilisateur(Utilisateur Utilisateur);
	List<Utilisateur> allUtilisateurs();
	Utilisateur getUtilisateurById(int id);
	Utilisateur updateUtilisateur(Utilisateur Utilisateur);
	List<Utilisateur> getUnvalidatedUtilisateurs();
	Utilisateur getUtilisateurLogin(String email, String password);
}
