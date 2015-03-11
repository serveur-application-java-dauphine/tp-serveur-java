package fr.dauphine.etrade.model;

// default package
// Generated 11 mars 2015 16:13:53 by Hibernate Tools 4.0.0

import java.math.BigDecimal;
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

/**
 * Ordre generated by hbm2java
 */
@Entity
@Table(name = "Ordre", schema = "etrade_titres")
public class Ordre implements java.io.Serializable {

	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private Long idOrder;
	private Produit produit;
	private DirectionOrdre directionOrdre;
	private StatusOrdre statusOrdre;
	private Portefeuille portefeuille;
	private TypeOrdre typeOrdre;
	private BigDecimal prix;
	private int quantite;
	private Date date;
	private Set<Transaction> transactionsForIdOrderVente = new HashSet<Transaction>(
			0);
	private Set<Transaction> transactionsForIdOrderAchat = new HashSet<Transaction>(
			0);

	public Ordre() {
	}

	public Ordre(Produit produit, DirectionOrdre directionOrdre,
			StatusOrdre statusOrdre, Portefeuille portefeuille,
			TypeOrdre typeOrdre, BigDecimal prix, int quantite, Date date) {
		this.produit = produit;
		this.directionOrdre = directionOrdre;
		this.statusOrdre = statusOrdre;
		this.portefeuille = portefeuille;
		this.typeOrdre = typeOrdre;
		this.prix = prix;
		this.quantite = quantite;
		this.date = date;
	}

	public Ordre(Produit produit, DirectionOrdre directionOrdre,
			StatusOrdre statusOrdre, Portefeuille portefeuille,
			TypeOrdre typeOrdre, BigDecimal prix, int quantite, Date date,
			Set<Transaction> transactionsForIdOrderVente,
			Set<Transaction> transactionsForIdOrderAchat) {
		this.produit = produit;
		this.directionOrdre = directionOrdre;
		this.statusOrdre = statusOrdre;
		this.portefeuille = portefeuille;
		this.typeOrdre = typeOrdre;
		this.prix = prix;
		this.quantite = quantite;
		this.date = date;
		this.transactionsForIdOrderVente = transactionsForIdOrderVente;
		this.transactionsForIdOrderAchat = transactionsForIdOrderAchat;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IdOrder", unique = true, nullable = false)
	public Long getIdOrder() {
		return this.idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdProduit", nullable = false)
	public Produit getProduit() {
		return this.produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdDirectionOrdre", nullable = false)
	public DirectionOrdre getDirectionOrdre() {
		return this.directionOrdre;
	}

	public void setDirectionOrdre(DirectionOrdre directionOrdre) {
		this.directionOrdre = directionOrdre;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdStatusOrder", nullable = false)
	public StatusOrdre getStatusOrdre() {
		return this.statusOrdre;
	}

	public void setStatusOrdre(StatusOrdre statusOrdre) {
		this.statusOrdre = statusOrdre;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdPortefeuille", nullable = false)
	public Portefeuille getPortefeuille() {
		return this.portefeuille;
	}

	public void setPortefeuille(Portefeuille portefeuille) {
		this.portefeuille = portefeuille;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdTypeOrder", nullable = false)
	public TypeOrdre getTypeOrdre() {
		return this.typeOrdre;
	}

	public void setTypeOrdre(TypeOrdre typeOrdre) {
		this.typeOrdre = typeOrdre;
	}

	@Column(name = "Prix", nullable = false, precision = 8)
	public BigDecimal getPrix() {
		return this.prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	@Column(name = "Quantite", nullable = false)
	public int getQuantite() {
		return this.quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Date", nullable = false, length = 19)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ordreByIdOrderVente")
	public Set<Transaction> getTransactionsForIdOrderVente() {
		return this.transactionsForIdOrderVente;
	}

	public void setTransactionsForIdOrderVente(
			Set<Transaction> transactionsForIdOrderVente) {
		this.transactionsForIdOrderVente = transactionsForIdOrderVente;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ordreByIdOrderAchat")
	public Set<Transaction> getTransactionsForIdOrderAchat() {
		return this.transactionsForIdOrderAchat;
	}

	public void setTransactionsForIdOrderAchat(
			Set<Transaction> transactionsForIdOrderAchat) {
		this.transactionsForIdOrderAchat = transactionsForIdOrderAchat;
	}

}