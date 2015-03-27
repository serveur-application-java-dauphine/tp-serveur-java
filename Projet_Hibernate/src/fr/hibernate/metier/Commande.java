package fr.hibernate.metier;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Commande {
	
	
	private int idCommande;
	private Enfant enfant;
	private Jouet jouet;
	private Date date_debut;
	private Date date_fin;

	//TODO: private static DAOCommande dao;
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IdCommande", unique = true, nullable = false)
	public int getIdCommande() {
		return idCommande;
	}
	/**
	 * @param id the id to set
	 */
	
	public void setIdCommanded(int idCommande) {
		this.idCommande = idCommande;
	}
	/**
	 * @return the enfant
	 */
	@ManyToOne
	@JoinColumn(name = "IdEnfant", nullable = false)
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
	@ManyToOne
	@JoinColumn(name = "IdJouet", nullable = false)
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
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Date_Debut", nullable = false, length = 19, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
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
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Date_Fin", nullable = false, length = 19)
	public Date getDate_fin() {
		return date_fin;
	}
	/**
	 * @param date_fin the date_fin to set
	 */
	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}
	
	//TODO: voir pour persister, synchroall, dao, etc...
	/*
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
	*/
	@Override
	public boolean equals(Object other) { 
    	if (this == other) return true; 
    	if ( !(other instanceof Enfant) ) return false;  
    	final Commande obj = (Commande) other; 
    	   if ( obj.getEnfant().getIdEnfant()!=getEnfant().getIdEnfant()||obj.getJouet().getIdJouet()!=getJouet().getIdJouet() ) 
    	     return false;           
    	return true; 
    }
	@Override
	public String toString() {
		return "[Enfant]= "+enfant+" [Jouet]= "+jouet+" // date d�but : "+date_debut+", date fin : "+date_fin;
	}

	
}
