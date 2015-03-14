package fr.dauphine.etrade.api;

import java.util.List;

import javax.ejb.Remote;

import fr.dauphine.etrade.model.Portefeuille;
import fr.dauphine.etrade.model.Utilisateur;

@Remote
public interface ServicesUtilisateur {
	Utilisateur addUtilisateur(Utilisateur utilisateur);
	Utilisateur delUtilisateur(Utilisateur utilisateur);
	List<Utilisateur> allUtilisateurs();
	Utilisateur getUtilisateurById(int id);
	Utilisateur getUtilisateurByEmail(String email);
	Utilisateur updateUtilisateur(Utilisateur utilisateur);
	List<Utilisateur> getUnvalidatedUtilisateurs();
	Portefeuille createPortefolio(Portefeuille p);
}
