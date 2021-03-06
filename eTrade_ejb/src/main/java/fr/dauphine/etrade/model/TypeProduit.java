package fr.dauphine.etrade.model;

// default package
// Generated 11 mars 2015 16:13:53 by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * TypeProduit generated by hbm2java
 */
@Entity
@Table(name = "Type_Produit", uniqueConstraints = @UniqueConstraint(columnNames = "Libelle"))
public class TypeProduit implements java.io.Serializable {

  /**
   * Default serialVersionUID
   */
  private static final long serialVersionUID = 1L;
  private Long idTypeProduit;
  private String libelle;
  private Set<Produit> produits = new HashSet<Produit>(0);

  public TypeProduit() {

  }

  public TypeProduit(String libelle) {
    this.libelle = libelle;
  }

  public TypeProduit(String libelle, Set<Produit> produits) {
    this.libelle = libelle;
    this.produits = produits;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "IdTypeProduit", unique = true, nullable = false)
  public Long getIdTypeProduit() {
    return this.idTypeProduit;
  }

  public void setIdTypeProduit(Long idTypeProduit) {
    this.idTypeProduit = idTypeProduit;
  }

  @Column(name = "Libelle", unique = true, nullable = false, length = 20)
  public String getLibelle() {
    return this.libelle;
  }

  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "typeProduit")
  public Set<Produit> getProduits() {
    return this.produits;
  }

  public void setProduits(Set<Produit> produits) {
    this.produits = produits;
  }

}
