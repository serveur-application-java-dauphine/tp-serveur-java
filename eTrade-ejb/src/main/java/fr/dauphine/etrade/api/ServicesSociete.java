package fr.dauphine.etrade.api;

import java.util.List;

import javax.ejb.Remote;

import fr.dauphine.etrade.model.Societe;

@Remote
public interface ServicesSociete {
	Societe addSociete(Societe societe);
	Societe delSociete(Societe societe);
	List<Societe> allSocietes();
	Societe getSocieteById(int id);
	Societe updateSociete(Societe societe);
}
