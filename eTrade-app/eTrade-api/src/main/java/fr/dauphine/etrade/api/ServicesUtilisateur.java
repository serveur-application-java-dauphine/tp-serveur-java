package fr.dauphine.etrade.api;

import java.util.List;

import javax.ejb.Remote;

import fr.dauphine.etrade.model.Portefeuille;
import fr.dauphine.etrade.model.Utilisateur;

@Remote
public interface ServicesUtilisateur {
	Response addUtilisateur(Utilisateur utilisateur);

	Response delUtilisateur(Utilisateur utilisateur);

	List<Utilisateur> allUtilisateurs();

	Utilisateur getUtilisateurById(long id);

	Utilisateur getUtilisateurByEmail(String email);

	Response updateUtilisateur(Utilisateur utilisateur);

	Response createPortefolio(Portefeuille p);

}
