package fr.dauphine.etrade.services;


import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import fr.dauphine.etrade.api.ServicesUtilisateur;
import fr.dauphine.etrade.model.Portefeuille;
import fr.dauphine.etrade.model.Utilisateur;
import fr.dauphine.etrade.persit.Connexion;

@Remote(ServicesUtilisateur.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesUtilisateurBean implements ServicesUtilisateur{

	
	private static final Logger LOG = Logger.getLogger(ServicesUtilisateurBean.class.getName());
	
	@Override
	public Utilisateur addUtilisateur(Utilisateur utilisateur) {
		Connexion.getInstance().insert(utilisateur);
		return utilisateur;
	}

	@Override
	public Utilisateur delUtilisateur(Utilisateur utilisateur) {
		Connexion.getInstance().delete(utilisateur);
		return utilisateur;
	}
	
	@Override
	public List<Utilisateur> allUtilisateurs() {
		String query = "FROM Utilisateur";
		return Connexion.getInstance().queryListResult(query, Utilisateur.class);
	}
	
	@Override
	public Utilisateur getUtilisateurById(long id) {
		return  Connexion.getInstance().find(Utilisateur.class, id);
	}

	@Override
	public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
		Connexion.getInstance().update(utilisateur);
		return utilisateur;
	}

	@Override
	public List<Utilisateur> getUnvalidatedUtilisateurs() {
		String query = "SELECT u FROM Utilisateur u WHERE u.validRole = ?";
		return Connexion.getInstance().queryListResult(query, Utilisateur.class, 0); 
	}
	
	@Override
	public Portefeuille createPortefolio(Portefeuille p){
		Connexion.getInstance().insert(p);
		return p;
	}

	@Override
	public Utilisateur getUtilisateurByEmail(String email) {
		String query = "SELECT u FROM Utilisateur u left join u.role left join u.societe left join u.portefeuille WHERE u.email= ?";
		return Connexion.getInstance().querySingleResult(query, Utilisateur.class, email);
	}
	
}
