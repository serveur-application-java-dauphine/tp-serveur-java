package fr.dauphine.etrade.managedbean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import fr.dauphine.etrade.api.ServicesEnchere;
import fr.dauphine.etrade.model.DirectionOrdre;
import fr.dauphine.etrade.model.Enchere;
import fr.dauphine.etrade.model.Ordre;
import fr.dauphine.etrade.model.Portefeuille;
import fr.dauphine.etrade.model.Produit;
import fr.dauphine.etrade.model.Societe;
import fr.dauphine.etrade.model.StatusOrdre;
import fr.dauphine.etrade.model.TypeOrdre;
import fr.dauphine.etrade.model.TypeProduit;

@ManagedBean
@RequestScoped
public class EnchereManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Enchere enchere;
	private String societeName;
	private boolean isPrixNotMeilleur = false;
	private BigDecimal prixEnchere = null;

	@EJB
	private ServicesEnchere se;
	
	@ManagedProperty("#{SessionUserManagedBean}")
	SessionUserManagedBean smb;
	
	public void setSmb(SessionUserManagedBean smb){
		this.smb = smb;
	}
	
	public Enchere getEnchere() {
		if(enchere==null){
			enchere = new Enchere();
			enchere.setPortefeuille(new Portefeuille());
			enchere.setOrdre(new Ordre());
			enchere.getOrdre().setDirectionOrdre(new DirectionOrdre());
			enchere.getOrdre().setStatusOrdre(new StatusOrdre());
			enchere.getOrdre().setTypeOrdre(new TypeOrdre());
			enchere.getOrdre().setProduit(new Produit());
			enchere.getOrdre().getProduit().setSociete(new Societe());
			enchere.getOrdre().getProduit().setTypeProduit(new TypeProduit());
		}
		return enchere;
	}
	
	public Enchere getEnchere(Long idEnchere){
		enchere = se.getEnchereById(idEnchere);
		return enchere;
	}
	
	public List<Enchere> getListEnchere() {
		List<Enchere> result = new ArrayList<Enchere>();
		if (null == societeName || societeName.equals("")) {
			result = se.allPendingEnchere();
		} else {
			result = se.encherePendingBySocieteName(societeName);
		}
		return result;
	}

	public void setEnchere(Enchere enchere) {
		this.enchere = enchere;
	}
	
	public List<Enchere> encheresByOrdre(Long idOrdre){
		return se.encheresByOrdre(idOrdre);
	}
	
	public void rencherir(){
		if((enchere.getOrdre().getDirectionOrdre().getIdDirectionOrdre().equals((long)2) && enchere.getPrix().compareTo(prixEnchere) < 0)
			|| (enchere.getOrdre().getDirectionOrdre().getIdDirectionOrdre().equals((long)1) && enchere.getPrix().compareTo(prixEnchere) > 0)){
			isPrixNotMeilleur = true;
		} else {
			isPrixNotMeilleur = false;
			enchere.setDateDebut(null);
			enchere.setPortefeuille(smb.getUtilisateur().getPortefeuille());
			enchere.setIdEnchere(null);
			enchere.setMain(false);
			enchere.setPrix(prixEnchere);
			se.addEnchere(enchere);
		}		
	}
	
	public String getSocieteName() {
		return societeName;
	}

	public void setSocieteName(String societeName) {
		this.societeName = societeName;
	}

	public boolean isPrixNotMeilleur() {
		return isPrixNotMeilleur;
	}

	public void isPrixNotMeilleur(boolean isPrixNotMeilleur) {
		this.isPrixNotMeilleur = isPrixNotMeilleur;
	}

	public BigDecimal getPrixEnchere() {
		return prixEnchere;
	}

	public void setPrixEnchere(BigDecimal prixEnchere) {
		this.prixEnchere = prixEnchere;
	}
	
	



}
