package fr.hibernate.metier;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import fr.hibernate.dao.DAOEnfant;

public class Enfant {
	
	private long id;
	private String nom;
	private String prenom;
	private Date ddn;
	private String adresse;
	private String ville;
	private String code_postal;
	private String tel;
	private String email;
	private ArrayList<Commande> commandes = new ArrayList<Commande>();
	private boolean created;
	private static DAOEnfant dao;
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
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;persist=false;
	}
	/**
	 * @return the ddn
	 */
	public Date getDdn() {
		return ddn;
	}
	/**
	 * @param ddn the ddn to set
	 */
	public void setDdn(Date ddn) {
		this.ddn = ddn;persist=false;
	}
	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}
	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;persist=false;
	}
	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}
	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;persist=false;
	}
	/**
	 * @return the code_postal
	 */
	public String getCode_postal() {
		return code_postal;
	}
	/**
	 * @param code_postal the code_postal to set
	 */
	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;persist=false;
	}
	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;persist=false;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;persist=false;
	}
	/**
	 * @return the commandes
	 */
	public ArrayList<Commande> getCommandes() {
		return commandes;
	}

	@Override
	public String toString() {
		return "id : "+id+", nom : "+nom+", prenom : "+prenom+", adresse : "+adresse+", code postal : "+code_postal
				+", ville :"+ville+", date de naissance : "+ddn+", tel : "+tel+", email : "+email +", created : "+created+", persitant : "+persist;
	}
	/**
	 * @param commandes the commandes to set
	 */
	public void setCommandes(ArrayList<Commande> commandes) {
		this.commandes = commandes;
	}
	
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
	
	private static DAOEnfant getDao(){
		if(dao==null)
			dao = new DAOEnfant();
		return dao;
	}
	
	@Override
	public boolean equals(Object other) { 
    	if (this == other) return true; 
    	if ( !(other instanceof Enfant) ) return false;  
    	final Enfant obj = (Enfant) other; 
    	   if ( obj.getId()!=getId() ) 
    	     return false;           
    	return true; 
    }
	
	public void synchroAll(Connection c){
		getDao().retrieveById(this, c);
	}
	
	public void addCommande(Commande c){
		commandes.add(c);
	}

}
