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
 * DirectionOrdre generated by hbm2java
 */
@Entity
@Table(name = "Direction_Ordre", uniqueConstraints = @UniqueConstraint(columnNames = "Libelle"))
public class DirectionOrdre implements java.io.Serializable {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private Long idDirectionOrdre;
	private String libelle;
	private Set<Ordre> ordres = new HashSet<Ordre>(0);

	public DirectionOrdre() {
	}

	public DirectionOrdre(String libelle) {
		this.libelle = libelle;
	}

	public DirectionOrdre(String libelle, Set<Ordre> ordres) {
		this.libelle = libelle;
		this.ordres = ordres;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IdDirectionOrdre", unique = true, nullable = false)
	public Long getIdDirectionOrdre() {
		return this.idDirectionOrdre;
	}

	public void setIdDirectionOrdre(Long idDirectionOrdre) {
		this.idDirectionOrdre = idDirectionOrdre;
	}

	@Column(name = "Libelle", unique = true, nullable = false, length = 20)
	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "directionOrdre")
	public Set<Ordre> getOrdres() {
		return this.ordres;
	}

	public void setOrdres(Set<Ordre> ordres) {
		this.ordres = ordres;
	}

}
