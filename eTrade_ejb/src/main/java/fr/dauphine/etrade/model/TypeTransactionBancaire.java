package fr.dauphine.etrade.model;

// default package
// Generated 11 mars 2015 16:13:53 by Hibernate Tools 4.0.0

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
 * TypeTransactionBancaire generated by hbm2java
 */
@Entity
@Table(name = "Type_Transaction_Bancaire", schema = "etrade_titres", uniqueConstraints = @UniqueConstraint(columnNames = "Libelle"))
public class TypeTransactionBancaire implements java.io.Serializable {

	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private Long idTypeTrBancaire;
	private String libelle;
	private Set<TransactionBancaire> transactionBancaires = new HashSet<TransactionBancaire>(
			0);

	public TypeTransactionBancaire() {
	}

	public TypeTransactionBancaire(String libelle) {
		this.libelle = libelle;
	}

	public TypeTransactionBancaire(String libelle,
			Set<TransactionBancaire> transactionBancaires) {
		this.libelle = libelle;
		this.transactionBancaires = transactionBancaires;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IdTypeTrBancaire", unique = true, nullable = false)
	public Long getIdTypeTrBancaire() {
		return this.idTypeTrBancaire;
	}

	public void setIdTypeTrBancaire(Long idTypeTrBancaire) {
		this.idTypeTrBancaire = idTypeTrBancaire;
	}

	@Column(name = "Libelle", unique = true, nullable = false, length = 20)
	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "typeTransactionBancaire")
	public Set<TransactionBancaire> getTransactionBancaires() {
		return this.transactionBancaires;
	}

	public void setTransactionBancaires(
			Set<TransactionBancaire> transactionBancaires) {
		this.transactionBancaires = transactionBancaires;
	}

}