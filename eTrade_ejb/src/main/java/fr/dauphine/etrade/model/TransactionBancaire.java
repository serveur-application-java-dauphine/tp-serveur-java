package fr.dauphine.etrade.model;

// default package
// Generated 11 mars 2015 16:13:53 by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
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
@Table(name = "Transaction_bancaire")
public class TransactionBancaire implements java.io.Serializable {

	@Override
	public String toString() {
		return "TransactionBancaire [idTrBancaire=" + idTrBancaire
				+ ", portefeuille=" + portefeuille + ", montant=" + montant
				+ ", date=" + date + "]";
	}

/**
   * Default serialVersionUID
   */
  private static final long serialVersionUID = 1L;
  private Long idTrBancaire;
  private Portefeuille portefeuille;
  private BigDecimal montant;
  private Date date;

  public TransactionBancaire() {

  }

  public TransactionBancaire(Portefeuille portefeuille, BigDecimal montant, Date date) {
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
  @Column(name = "Date", nullable = false, length = 19, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  public Date getDate() {
    return this.date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

}
