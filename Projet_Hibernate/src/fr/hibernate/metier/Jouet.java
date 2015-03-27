package fr.hibernate.metier;

import java.sql.Connection;
import java.util.ArrayList;

import javax.persistence.Entity;

import fr.hibernate.dao.DAOJouet;

@Entity
public class Jouet {

	
	private long idJouet;
	private String nom;
	private String description;
	private boolean created;
	private static DAOJouet dao;
	private ArrayList<Commande> commandes = new ArrayList<Commande>();
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
	 * @return the id
	 */
	public long getIdJouet() {
		return idJouet;
	}
	/**
	 * @param id the id to set
	 */
	public void setIdJouet(long idJouet) {
		this.idJouet = idJouet;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;persist=false;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;persist=false;
	}
	/**
	 * @return the persist
	 */
	public boolean isPersist() {
		return persist;
	}
	/**
	 * @param persist the persist to set
	 */
	public void setPersist(boolean persist) {
		this.persist = persist;
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
	
	private static DAOJouet getDao(){
		if(dao==null)
			dao = new DAOJouet();
		return dao;
	}
	
	public void synchroAll(Connection c){
		getDao().retrieveById(this, c);
	}
	
	@Override
	public boolean equals(Object other) { 
    	if (this == other) return true; 
    	if ( !(other instanceof Jouet) ) return false;  
    	final Jouet obj = (Jouet) other; 
    	   if ( obj.getIdJouet()!=getIdJouet() ) 
    	     return false;           
    	return true; 
    }
	@Override
	public String toString() {
		return " id : "+idJouet+", nom : "+nom+", description : "+description+", created : "+created+", persit : "+persist;
	}
	public void addCommande(Commande c){
		commandes.add(c);
	}
	
}
