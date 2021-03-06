package fr.dauphine.etrade.model;

// default package
// Generated 15 mars 2015 14:57:54 by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Produit generated by hbm2java
 */
@Entity
@Table(name = "Produit")
public class Produit implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long idProduit;
	private TypeProduit typeProduit;
	private Societe societe;
	private Date maturite;
	private BigDecimal coupon;
	private BigDecimal taux;
	private BigDecimal strike;
	private BigDecimal volatilite;
	private Set<Ordre> ordres = new HashSet<Ordre>(0);

	private int quantite;

	public Produit() {

	}

	public Produit(TypeProduit typeProduit, Societe societe) {
		this.typeProduit = typeProduit;
		this.societe = societe;
	}

	public Produit(TypeProduit typeProduit, Societe societe, Date maturite, BigDecimal coupon,
			BigDecimal taux, BigDecimal strike, BigDecimal volatilite, Set<Ordre> ordres) {
		this.typeProduit = typeProduit;
		this.societe = societe;
		this.maturite = maturite;
		this.coupon = coupon;
		this.taux = taux;
		this.strike = strike;
		this.volatilite = volatilite;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "Maturite", length = 10)
	public Date getMaturite() {
		return this.maturite;
	}

	public void setMaturite(Date maturite) {
		this.maturite = maturite;
	}

	@Column(name = "Coupon", precision = 10)
	public BigDecimal getCoupon() {
		return this.coupon;
	}

	public void setCoupon(BigDecimal coupon) {
		this.coupon = coupon;
	}

	@Column(name = "Taux", precision = 10)
	public BigDecimal getTaux() {
		return this.taux;
	}

	public void setTaux(BigDecimal taux) {
		this.taux = taux;
	}

	@Column(name = "Strike", precision = 10)
	public BigDecimal getStrike() {
		return this.strike;
	}

	public void setStrike(BigDecimal strike) {
		this.strike = strike;
	}

	@Column(name = "Volatilite", precision = 10)
	public BigDecimal getVolatilite() {
		return this.volatilite;
	}

	public void setVolatilite(BigDecimal volatilite) {
		this.volatilite = volatilite;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "produit")
	public Set<Ordre> getOrdres() {
		return this.ordres;
	}

	public void setOrdres(Set<Ordre> ordres) {
		this.ordres = ordres;
	}

	/**
	 * Returns the product label
	 * 
	 * @return result
	 */
	@Transient
	public String getLibelleProduit() {
		String result = "";
		if (this.typeProduit.getIdTypeProduit() == 1) {
			result = this.typeProduit.getLibelle();
		} else if (this.typeProduit.getIdTypeProduit() == 2) {
			result = this.typeProduit.getLibelle() + " K" + this.getStrike() + " T"
					+ DateFormat.getDateInstance(DateFormat.MEDIUM).format(this.getMaturite());
		} else if (this.typeProduit.getIdTypeProduit() == 3) {
			result = this.typeProduit.getLibelle() + " C" + this.getCoupon() + "% T"
					+ DateFormat.getDateInstance(DateFormat.MEDIUM).format(this.getMaturite());
		}
		return result;
	}

	/**
	 * @return the quantite
	 */
	@Transient
	public int getQuantite() {
		return quantite;
	}


	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

}
