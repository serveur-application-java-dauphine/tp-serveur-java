package fr.dauphine.etrade.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "Actualite")
public class Actualite implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  private Long idActualite;
  private Societe societe;
  private Utilisateur utilisateur;
  private String file;
  private String titre;
  private Date dateCreation;
  private Date dateModification;

  @Transient
  private String content;

  public Actualite() {
  }

  public Actualite(Societe societe, Utilisateur utilisateur, String file, String titre,
      Date dateCreation, Date dateModification) {
    this.societe = societe;
    this.utilisateur = utilisateur;
    this.file = file;
    this.titre = titre;
    this.dateCreation = dateCreation;
    this.dateModification = dateModification;
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

  @Column(name = "titre", nullable = false, length = 100)
  public String getTitre() {
    return this.titre;
  }

  public void setTitre(String titre) {
    this.titre = titre;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "date_creation", nullable = false, length = 19, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  public Date getDateCreation() {
    return dateCreation;
  }

  public void setDateCreation(Date dateCreation) {
    this.dateCreation = dateCreation;
  }
  
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "date_modification", length = 19)
  public Date getDateModification() {
    return dateModification;
  }

  public void setDateModification(Date dateModification) {
    this.dateModification = dateModification;
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
