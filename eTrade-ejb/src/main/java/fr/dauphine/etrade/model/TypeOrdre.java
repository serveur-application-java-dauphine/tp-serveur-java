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
 * TypeOrdre generated by hbm2java
 */
@Entity
@Table(name = "Type_Ordre", catalog = "etrade_titres", uniqueConstraints = @UniqueConstraint(columnNames = "Libelle"))
public class TypeOrdre implements java.io.Serializable {

	private Long idTypeOrder;
	private String libelle;
	private Set<Ordre> ordres = new HashSet<Ordre>(0);

	public TypeOrdre() {
	}

	public TypeOrdre(String libelle) {
		this.libelle = libelle;
	}

	public TypeOrdre(String libelle, Set<Ordre> ordres) {
		this.libelle = libelle;
		this.ordres = ordres;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IdTypeOrder", unique = true, nullable = false)
	public Long getIdTypeOrder() {
		return this.idTypeOrder;
	}

	public void setIdTypeOrder(Long idTypeOrder) {
		this.idTypeOrder = idTypeOrder;
	}

	@Column(name = "Libelle", unique = true, nullable = false, length = 20)
	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "typeOrdre")
	public Set<Ordre> getOrdres() {
		return this.ordres;
	}

	public void setOrdres(Set<Ordre> ordres) {
		this.ordres = ordres;
	}

}
