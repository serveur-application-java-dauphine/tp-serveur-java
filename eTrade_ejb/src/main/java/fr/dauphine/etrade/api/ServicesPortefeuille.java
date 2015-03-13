package fr.dauphine.etrade.api;

import javax.ejb.Remote;

import fr.dauphine.etrade.model.Portefeuille;

@Remote
public interface ServicesPortefeuille {
	Portefeuille getPortefeuilleByUserEmail(String email);
}
