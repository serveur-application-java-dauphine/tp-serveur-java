package fr.dauphine.etrade.model;

// default package
// Generated 5 mars 2015 14:47:28 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Societe generated by hbm2java
 */
@Entity
@Table(name = "Societe", catalog = "etrade_titres", uniqueConstraints = @UniqueConstraint(columnNames = "Name"))
public class Societe implements java.io.Serializable {

	
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private Long idSociete;
	private String name;
	private Set<Produit> produits = new HashSet<Produit>(0);
	private Set<Utilisateur> utilisateurs = new HashSet<Utilisateur>(0);
	private Set<Actualite> actualites = new HashSet<Actualite>(0);

	public Societe() {
	}

	public Societe(String name) {
		this.name = name;
	}

	public Societe(String name, Set<Produit> produits,
			Set<Utilisateur> utilisateurs, Set<Actualite> actualites) {
		this.name = name;
		this.produits = produits;
		this.utilisateurs = utilisateurs;
		this.actualites = actualites;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IdSociete", unique = true, nullable = false)
	public Long getIdSociete() {
		return this.idSociete;
	}

	public void setIdSociete(Long idSociete) {
		this.idSociete = idSociete;
	}

	@Column(name = "Name", unique = true, nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "societe")
	public Set<Produit> getProduits() {
		return this.produits;
	}

	public void setProduits(Set<Produit> produits) {
		this.produits = produits;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "societe")
	public Set<Utilisateur> getUtilisateurs() {
		return this.utilisateurs;
	}

	public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "societe")
	public Set<Actualite> getActualites() {
		return this.actualites;
	}

	public void setActualites(Set<Actualite> actualites) {
		this.actualites = actualites;
	}

}
