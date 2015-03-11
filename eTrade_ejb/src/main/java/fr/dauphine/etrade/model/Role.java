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
 * Role generated by hbm2java
 */
@Entity
@Table(name = "Role", schema = "etrade_titres", uniqueConstraints = @UniqueConstraint(columnNames = "Libelle"))
public class Role implements java.io.Serializable {

	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private Long idRole;
	private String libelle;
	private Set<Utilisateur> utilisateurs = new HashSet<Utilisateur>(0);

	public Role() {
	}

	public Role(String libelle) {
		this.libelle = libelle;
	}

	public Role(String libelle, Set<Utilisateur> utilisateurs) {
		this.libelle = libelle;
		this.utilisateurs = utilisateurs;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IdRole", unique = true, nullable = false)
	public Long getIdRole() {
		return this.idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	@Column(name = "Libelle", unique = true, nullable = false, length = 20)
	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	public Set<Utilisateur> getUtilisateurs() {
		return this.utilisateurs;
	}

	public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

}
