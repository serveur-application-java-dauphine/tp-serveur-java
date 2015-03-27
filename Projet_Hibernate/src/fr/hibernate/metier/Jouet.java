package fr.hibernate.metier;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import fr.hibernate.dao.DAOGenerique;


@Entity
public class Jouet {


	private long idJouet;
	private String nom;
	private String description;
	private ArrayList<Commande> commandes = new ArrayList<Commande>();


	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IdJouet", unique = true, nullable = false)
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
	@Column(name = "Nom", unique = true, nullable = false)
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
	 * @return the description
	 */
	@Column(name = "Description", unique = true, nullable = false)
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return the commandes
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "Jouet")
	public ArrayList<Commande> getCommandes() {
		return commandes;
	}
	public void setCommandes(ArrayList<Commande> commandes) {
		this.commandes = commandes;
	}

	public boolean persister(){
		return DAOGenerique.insert(this)==1;
	}
	public boolean delete (){
		return DAOGenerique.delete(this)==1;
	}
	public boolean update (){
		return DAOGenerique.update(this)==1;
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
		return " id : "+idJouet+", nom : "+nom+", description : "+description;
	}


}
