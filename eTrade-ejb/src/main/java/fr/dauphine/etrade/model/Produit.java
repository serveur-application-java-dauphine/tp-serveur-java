package fr.dauphine.etrade.model;

// default package
// Generated 5 mars 2015 12:36:10 by Hibernate Tools 4.0.0

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

/**
 * Produit generated by hbm2java
 */
@Entity
@Table(name = "Produit", catalog = "etrade_titres")
public class Produit implements java.io.Serializable {

	private Long idProduit;
	private TypeProduit typeProduit;
	private Societe societe;
	private Set<Ordre> ordres = new HashSet<Ordre>(0);

	public Produit() {
	}

	public Produit(TypeProduit typeProduit, Societe societe) {
		this.typeProduit = typeProduit;
		this.societe = societe;
	}

	public Produit(TypeProduit typeProduit, Societe societe, Set<Ordre> ordres) {
		this.typeProduit = typeProduit;
		this.societe = societe;
		this.ordres = ordres;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IdProduit", unique = true, nullable = false)
	public Long getIdProduit() {
		return this.idProduit;
	}

	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdTypeProduit", nullable = false)
	public TypeProduit getTypeProduit() {
		return this.typeProduit;
	}

	public void setTypeProduit(TypeProduit typeProduit) {
		this.typeProduit = typeProduit;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdSociete", nullable = false)
	public Societe getSociete() {
		return this.societe;
	}

	public void setSociete(Societe societe) {
		this.societe = societe;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "produit")
	public Set<Ordre> getOrdres() {
		return this.ordres;
	}

	public void setOrdres(Set<Ordre> ordres) {
		this.ordres = ordres;
	}

}
