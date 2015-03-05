package fr.dauphine.etrade.model;

// default package
// Generated 5 mars 2015 12:36:10 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Portefeuille generated by hbm2java
 */
@Entity
@Table(name = "Portefeuille", catalog = "etrade_titres")
public class Portefeuille implements java.io.Serializable {

	private long idPortefeuille;
	private Utilisateur utilisateur;
	private String description;
	private Set<Ordre> ordres = new HashSet<Ordre>(0);
	private Set<TransactionBancaire> transactionBancaires = new HashSet<TransactionBancaire>(
			0);
	private Set<Utilisateur> utilisateurs = new HashSet<Utilisateur>(0);

	public Portefeuille() {
	}

	public Portefeuille(long idPortefeuille) {
		this.idPortefeuille = idPortefeuille;
	}

	public Portefeuille(long idPortefeuille, Utilisateur utilisateur,
			String description, Set<Ordre> ordres,
			Set<TransactionBancaire> transactionBancaires,
			Set<Utilisateur> utilisateurs) {
		this.idPortefeuille = idPortefeuille;
		this.utilisateur = utilisateur;
		this.description = description;
		this.ordres = ordres;
		this.transactionBancaires = transactionBancaires;
		this.utilisateurs = utilisateurs;
	}

	@Id
	@Column(name = "IdPortefeuille", unique = true, nullable = false)
	public long getIdPortefeuille() {
		return this.idPortefeuille;
	}

	public void setIdPortefeuille(long idPortefeuille) {
		this.idPortefeuille = idPortefeuille;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdUtilisateur")
	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Column(name = "Description", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "portefeuille")
	public Set<Ordre> getOrdres() {
		return this.ordres;
	}

	public void setOrdres(Set<Ordre> ordres) {
		this.ordres = ordres;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "portefeuille")
	public Set<TransactionBancaire> getTransactionBancaires() {
		return this.transactionBancaires;
	}

	public void setTransactionBancaires(
			Set<TransactionBancaire> transactionBancaires) {
		this.transactionBancaires = transactionBancaires;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "portefeuille")
	public Set<Utilisateur> getUtilisateurs() {
		return this.utilisateurs;
	}

	public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

}
