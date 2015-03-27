package fr.hibernate.metier;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Enfant {
	
	private long idEnfant;
	private String nom;
	private String prenom;
	private Date ddn;
	private String adresse;
	private String ville;
	private String code_postal;
	private String tel;
	private String email;
	private ArrayList<Commande> commandes = new ArrayList<Commande>();
	//TODO: A voir pour private static DAOEnfant dao;

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IdEnfant", unique = true, nullable = false)
	public long getIdEnfant() {
		return idEnfant;
	}
	/**
	 * @param id the id to set
	 */
	public void setIdEnfant(long idEnfant) {
		this.idEnfant = idEnfant;
	}
	/**
	 * @return the nom
	 */
	@Column(name = "Nom", unique = false, nullable = false)
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the prenom
	 */
	@Column(name = "Prenom", unique = false, nullable = false)
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @return the ddn
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Date_Naissance", nullable = false, length = 19)
	public Date getDdn() {
		return ddn;
	}
	/**
	 * @param ddn the ddn to set
	 */
	public void setDdn(Date ddn) {
		this.ddn = ddn;
	}
	/**
	 * @return the adresse
	 */
	@Column(name = "Adresse", unique = false, nullable = false)
	public String getAdresse() {
		return adresse;
	}
	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	/**
	 * @return the ville
	 */
	@Column(name = "Ville", unique = false, nullable = false)
	public String getVille() {
		return ville;
	}
	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
	/**
	 * @return the code_postal
	 */
	@Column(name = "Code_Postal", unique = false, nullable = false)
	public String getCode_postal() {
		return code_postal;
	}
	/**
	 * @param code_postal the code_postal to set
	 */
	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}
	/**
	 * @return the tel
	 */
	@Column(name = "Telephone", unique = false, nullable = false)
	public String getTel() {
		return tel;
	}
	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * @return the email
	 */
	@Column(name = "Email", unique = false, nullable = false)
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the commandes
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "Enfant")
	public ArrayList<Commande> getCommandes() {
		return commandes;
	}

	/**
	 * @param commandes the commandes to set
	 */
	public void setCommandes(ArrayList<Commande> commandes) {
		this.commandes = commandes;
	}

	//TODO: Voir pour persister, synchroall, addcommande etc...
	/*public void persister(Connection c){
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
	}*/
	
	@Override
	public boolean equals(Object other) { 
    	if (this == other) return true; 
    	if ( !(other instanceof Enfant) ) return false;  
    	final Enfant obj = (Enfant) other; 
    	   if ( obj.getIdEnfant()!=getIdEnfant() ) 
    	     return false;           
    	return true; 
    }
	@Override
	public String toString() {
		return "id : "+idEnfant+", nom : "+nom+", prenom : "+prenom+", adresse : "+adresse+", code postal : "+code_postal
				+", ville :"+ville+", date de naissance : "+ddn+", tel : "+tel+", email : "+email;
	}
	
	/*public void synchroAll(Connection c){
		getDao().retrieveById(this, c);
	}
	
	public void addCommande(Commande c){
		commandes.add(c);
	}*/

}
