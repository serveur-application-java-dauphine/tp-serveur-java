package fr.dauphine.etrade.model;

// default package
// Generated 5 mars 2015 14:47:28 by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TransactionBancaire generated by hbm2java
 */
@Entity
@Table(name = "Transaction_bancaire", catalog = "etrade_titres")
public class TransactionBancaire implements java.io.Serializable {
	
	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private Long idTrBancaire;
	private TypeTransactionBancaire typeTransactionBancaire;
	private Portefeuille portefeuille;
	private BigDecimal montant;
	private Date date;

	public TransactionBancaire() {
	}

	public TransactionBancaire(TypeTransactionBancaire typeTransactionBancaire,
			Portefeuille portefeuille, BigDecimal montant, Date date) {
		this.typeTransactionBancaire = typeTransactionBancaire;
		this.portefeuille = portefeuille;
		this.montant = montant;
		this.date = date;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IdTrBancaire", unique = true, nullable = false)
	public Long getIdTrBancaire() {
		return this.idTrBancaire;
	}

	public void setIdTrBancaire(Long idTrBancaire) {
		this.idTrBancaire = idTrBancaire;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdTypeTrBancaire", nullable = false)
	public TypeTransactionBancaire getTypeTransactionBancaire() {
		return this.typeTransactionBancaire;
	}

	public void setTypeTransactionBancaire(
			TypeTransactionBancaire typeTransactionBancaire) {
		this.typeTransactionBancaire = typeTransactionBancaire;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdPortefeuille", nullable = false)
	public Portefeuille getPortefeuille() {
		return this.portefeuille;
	}

	public void setPortefeuille(Portefeuille portefeuille) {
		this.portefeuille = portefeuille;
	}

	@Column(name = "Montant", nullable = false, precision = 8)
	public BigDecimal getMontant() {
		return this.montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Date", nullable = false, length = 19)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
