package fr.dauphine.etrade.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Actualite")
public class Actualite implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	private Long idActualite;
	@Column(name="idSociete")
	private Societe societe;
	@Column(name="idUtilisateur")
	private Utilisateur utilisateur;
	private String file;
	private String titre;
	private Date date_creation;
	private Date date_modification;
	
	@Transient
	private String content;

	public Actualite() {
	}

	public Actualite(Societe societe, Utilisateur utilisateur, String file, String titre, Date date_creation, Date date_modification) {
		this.societe = societe;
		this.utilisateur = utilisateur;
		this.file = file;
		this.titre = titre;
		this.date_creation = date_creation;
		this.date_modification = date_modification;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IdActualite", unique = true, nullable = false)
	public Long getIdActualite() {
		return this.idActualite;
	}

	public void setIdActualite(Long idActualite) {
		this.idActualite = idActualite;
	}

	@ManyToOne
	@JoinColumn(name = "IdSociete", nullable = false)
	public Societe getSociete() {
		return this.societe;
	}

	public void setSociete(Societe societe) {
		this.societe = societe;
	}

	@ManyToOne
	@JoinColumn(name = "IdUtilisateur", nullable = false)
	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Column(name = "File", nullable = false, length = 200)
	public String getFile() {
		return this.file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	@Column(name="titre", nullable = false, length = 100)
	public String getTitre(){
		return this.titre;
	}
	
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	@Column(name="date_creation", nullable = false,columnDefinition="DEFAULT CURRENT_TIMESTAMP")
	public Date getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}

	@Column(name="date_modification", columnDefinition="ON UPDATE CURRENT_TIMESTAMP")
	public Date getDate_modification() {
		return date_modification;
	}

	public void setDate_modification(Date date_modification) {
		this.date_modification = date_modification;
	}
	
	@Transient
	public String getContent() {
		return content;
	}

	@Transient
	public void setContent(String content) {
		this.content = content;
	}


}
