package fr.hibernate.metier;

import java.sql.Connection;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import fr.hibernate.dao.DAOCommande;

@Entity
public class Commande {
	
	
	@Override
	public String toString() {
		return "[Enfant]= "+enfant+" [Jouet]= "+jouet+" // date début : "+date_debut+", date fin : "+date_fin;
	}
	private int id;
	private Enfant enfant;
	private Jouet jouet;
	private Date date_debut;
	private Date date_fin;
	
	private boolean created;
	private static DAOCommande dao;
	/**
	 * @return the created
	 */
	public boolean isCreated() {
		return created;
	}
	/**
	 * @param created the created to set
	 */
	public void setCreated(boolean created) {
		this.created = created;
	}
	private boolean persist;
	
	/**
	 * @return the persit
	 */
	public boolean isPersit() {
		return persist;
	}
	/**
	 * @param persit the persit to set
	 */
	public void setPersist(boolean persist) {
		this.persist = persist;
	}
	
	
	/**
	 * @return the enfant
	 */
	public Enfant getEnfant() {
		return enfant;
	}
	/**
	 * @param enfant the enfant to set
	 */
	public void setEnfant(Enfant enfant) {
		this.enfant = enfant;
	}
	/**
	 * @return the jouet
	 */
	public Jouet getJouet() {
		return jouet;
	}
	/**
	 * @param jouet the jouet to set
	 */
	public void setJouet(Jouet jouet) {
		this.jouet = jouet;
	}
	/**
	 * @return the date_debut
	 */
	public Date getDate_debut() {
		return date_debut;
	}
	/**
	 * @param date_debut the date_debut to set
	 */
	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}
	/**
	 * @return the date_fin
	 */
	public Date getDate_fin() {
		return date_fin;
	}
	/**
	 * @param date_fin the date_fin to set
	 */
	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}
	
	public void persister(Connection c){
		if (persist)
			return;
		if (!created)
			getDao().insert(this, c);
		else
			getDao().update(this, c);	
	}
	public boolean delete (Connection c){
		return getDao().delete(this, c)==1;
	}
	
	public void synchro(Connection c){
		getDao().retrieveById(this, c, DAOCommande.mode_rapide);
	}
	public void synchroAll(Connection c){
		getDao().retrieveById(this, c, DAOCommande.mode_lent);
	}
	private static DAOCommande getDao(){
		if(dao==null)
			dao = new DAOCommande();
		return dao;
	}
	
	@Override
	public boolean equals(Object other) { 
    	if (this == other) return true; 
    	if ( !(other instanceof Enfant) ) return false;  
    	final Commande obj = (Commande) other; 
    	   if ( obj.getEnfant().getId()!=getEnfant().getId()||obj.getJouet().getId()!=getJouet().getId() ) 
    	     return false;           
    	return true; 
    }
	/**
	 * @return the id
	 */
	@Id
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
}
