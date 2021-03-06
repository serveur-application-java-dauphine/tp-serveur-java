package fr.dauphine.etrade.model;

// default package
// Generated 11 mars 2015 16:13:53 by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * Utilisateur generated by hbm2java
 */
@Entity
@Table(name = "Utilisateur", uniqueConstraints = @UniqueConstraint(columnNames = "Email"))
public class Utilisateur implements java.io.Serializable {

  @Override
  public String toString() {
    return lastname + " " + firstname;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((adress == null) ? 0 : adress.hashCode());
    result = prime * result + ((birthdate == null) ? 0 : birthdate.hashCode());
    result = prime * result + ((city == null) ? 0 : city.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
    result = prime * result + ((idUtilisateur == null) ? 0 : idUtilisateur.hashCode());
    result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + (validRole ? 1231 : 1237);
    result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Utilisateur other = (Utilisateur) obj;
    if (adress == null) {
      if (other.adress != null) {
        return false;
      }
    } else if (!adress.equals(other.adress)) {
      return false;
    }
    if (birthdate == null) {
      if (other.birthdate != null) {
        return false;
      }
    } /*else if (!birthdate.equals(other.birthdate)) {
      return false;
    }*/
    if (city == null) {
      if (other.city != null) {
        return false;
      }
    } else if (!city.equals(other.city)) {
      return false;
    }
    if (email == null) {
      if (other.email != null) {
        return false;
      }
    } else if (!email.equals(other.email)) {
      return false;
    }
    if (firstname == null) {
      if (other.firstname != null) {
        return false;
      }
    } else if (!firstname.equals(other.firstname)) {
      return false;
    }
    if (idUtilisateur == null) {
      if (other.idUtilisateur != null) {
        return false;
      }
    } else if (!idUtilisateur.equals(other.idUtilisateur)) {
      return false;
    }
    if (lastname == null) {
      if (other.lastname != null) {
        return false;
      }
    } else if (!lastname.equals(other.lastname)) {
      return false;
    }
    if (password == null) {
      if (other.password != null) {
        return false;
      }
    } else if (!password.equals(other.password)) {
      return false;
    }
    if (validRole != other.validRole) {
      return false;
    }
    if (zipcode == null) {
      if (other.zipcode != null) {
        return false;
      }
    } else if (!zipcode.equals(other.zipcode)) {
      return false;
    }
    return true;
  }

  private static final long serialVersionUID = 1L;
  private Long idUtilisateur;
  private Societe societe;
  private Portefeuille portefeuille;
  private Role role;
  private String lastname;
  private String firstname;
  private String email;
  private String password;
  private Date birthdate;
  private String adress;
  private String zipcode;
  private String city;
  private boolean validRole;

  // private Set<Actualite> actualites = new HashSet<Actualite>(0);

  public Utilisateur() {
  }

  public Utilisateur(Role role, String lastname, String firstName, String email, String password,
      Date birthdate, String adress, String zipcode, String city, boolean validRole) {
    this.role = role;
    this.lastname = lastname;
    this.firstname = firstName;
    this.email = email;
    this.password = password;
    this.birthdate = birthdate;
    this.adress = adress;
    this.zipcode = zipcode;
    this.city = city;
    this.validRole = validRole;
  }

  public Utilisateur(Societe societe, Portefeuille portefeuille, Role role, String lastname,
      String firstName, String email, String password, Date birthdate, String adress,
      String zipcode, String city, boolean validRole) {// , Set<Actualite> actualites) {
    this.societe = societe;
    this.portefeuille = portefeuille;
    this.role = role;
    this.lastname = lastname;
    this.firstname = firstName;
    this.email = email;
    this.password = password;
    this.birthdate = birthdate;
    this.adress = adress;
    this.zipcode = zipcode;
    this.city = city;
    this.validRole = validRole;
    // this.actualites = actualites;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "IdUtilisateur", unique = true, nullable = false)
  public Long getIdUtilisateur() {
    return this.idUtilisateur;
  }

  public void setIdUtilisateur(Long idUtilisateur) {
    this.idUtilisateur = idUtilisateur;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "IdSociete")
  public Societe getSociete() {
    return this.societe;
  }

  public void setSociete(Societe societe) {
    this.societe = societe;
  }

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "IdPortefeuille")
  public Portefeuille getPortefeuille() {
    return this.portefeuille;
  }

  public void setPortefeuille(Portefeuille portefeuille) {
    this.portefeuille = portefeuille;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "IdRole", nullable = false)
  public Role getRole() {
    return this.role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Column(name = "Lastname", nullable = false, length = 40)
  public String getLastname() {
    return this.lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  @Column(name = "Firstname", nullable = false, length = 40)
  public String getFirstname() {
    return this.firstname;
  }

  public void setFirstname(String firstName) {
    this.firstname = firstName;
  }

  @Column(name = "Email", unique = true, nullable = false, length = 40)
  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Column(name = "Password", nullable = false, length = 20)
  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "Birthdate", nullable = false, length = 19)
  public Date getBirthdate() {
    return this.birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  @Column(name = "Adress", nullable = false, length = 100)
  public String getAdress() {
    return this.adress;
  }

  public void setAdress(String adress) {
    this.adress = adress;
  }

  @Column(name = "Zipcode", nullable = false, length = 10)
  public String getZipcode() {
    return this.zipcode;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }

  @Column(name = "City", nullable = false, length = 40)
  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  @Column(name = "ValidRole", nullable = false)
  public boolean isValidRole() {
    return this.validRole;
  }

  public void setValidRole(boolean validRole) {
    this.validRole = validRole;
  }

  /*
   * @OneToMany(fetch = FetchType.LAZY, mappedBy = "utilisateur") public Set<Actualite>
   * getActualites() { return this.actualites; }
   * 
   * public void setActualites(Set<Actualite> actualites) { this.actualites = actualites; }
   */

  @Transient
  public String getFullName() {
    return firstname + " " + lastname;
  }

}
