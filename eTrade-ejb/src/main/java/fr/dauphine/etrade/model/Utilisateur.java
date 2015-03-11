package fr.dauphine.etrade.model;

// default package
// Generated 5 mars 2015 14:47:28 by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Utilisateur generated by hbm2java
 */
@Entity
@Table(name = "Utilisateur", uniqueConstraints = @UniqueConstraint(columnNames = "Mail"))
public class Utilisateur implements java.io.Serializable {

	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idUtilisateur;
	private Societe societe;
	private Portefeuille portefeuille;
	private Role role;
	private String lastname;
	private String name;
	private String mail;
	private String password;
	private Date birthdate;
	private String adress;
	private String zipcode;
	private String city;
	private boolean validRole;
	private Set<Actualite> actualites = new HashSet<Actualite>(0);

	public Utilisateur() {
		this.validRole = false;
	}

	public Utilisateur(Role role, String lastname, String name, String mail,
			String password, Date birthdate, String adress, String zipcode,
			String city, boolean validRole) {
		this.role = role;
		this.lastname = lastname;
		this.name = name;
		this.mail = mail;
		this.password = password;
		this.birthdate = birthdate;
		this.adress = adress;
		this.zipcode = zipcode;
		this.city = city;
		this.validRole = validRole;
	}

	public Utilisateur(Societe societe, Portefeuille portefeuille, Role role,
			String lastname, String name, String mail, String password,
			Date birthdate, String adress, String zipcode, String city, boolean validRole,
			Set<Portefeuille> portefeuilles, Set<Actualite> actualites) {
		this.societe = societe;
		this.portefeuille = portefeuille;
		this.role = role;
		this.lastname = lastname;
		this.name = name;
		this.mail = mail;
		this.password = password;
		this.birthdate = birthdate;
		this.adress = adress;
		this.zipcode = zipcode;
		this.city = city;
		this.validRole = validRole;
		this.actualites = actualites;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IdUtilisateur", unique = true, nullable = false)
	public Long getIdUtilisateur() {
		return this.idUtilisateur;
	}

	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IdSociete")
	public Societe getSociete() {
		return this.societe;
	}

	public void setSociete(Societe societe) {
		this.societe = societe;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdPortefeuille")
	public Portefeuille getPortefeuille() {
		return this.portefeuille;
	}

	public void setPortefeuille(Portefeuille portefeuille) {
		this.portefeuille = portefeuille;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IdRole", nullable = false)
	public Role getRole() {
		return this.role; 
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Column(name = "Lastname", nullable = false, length = 40)
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(name = "Name", nullable = false, length = 40)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Mail", unique = true, nullable = false, length = 40)
	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Column(name = "Password", nullable = false, length = 20)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Birthdate", nullable = false, length = 19)
	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Column(name = "Adress", nullable = false, length = 100)
	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	@Column(name = "Zipcode", nullable = false, length = 10)
	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Column(name = "City", nullable = false, length = 40)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name="validRole", nullable = false, length=1)
	public boolean getValidRole(){
		return this.validRole;
	}
	
	public void setValidRole(boolean validRole){
		this.validRole = validRole;
	}
	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "utilisateur")
	public Set<Actualite> getActualites() {
		return this.actualites;
	}

	public void setActualites(Set<Actualite> actualites) {
		this.actualites = actualites;
	}

}
