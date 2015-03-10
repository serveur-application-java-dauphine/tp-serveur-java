package fr.dauphine.etrade.model;

// default package
// Generated 5 mars 2015 14:47:28 by Hibernate Tools 4.0.0


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Administrateur generated by hbm2java
 */
@Entity
@Table(name="Administrateur")
public class Administrateur  implements java.io.Serializable {


    /**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private long id;
     private String mail;
     private String name;
     private String password;

    public Administrateur() {
    }

	
    public Administrateur(long id) {
        this.id = id;
    }
    public Administrateur(long id, String mail, String name, String password) {
       this.id = id;
       this.mail = mail;
       this.name = name;
       this.password = password;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    
    @Column(name="mail")
    public String getMail() {
        return this.mail;
    }
    
    public void setMail(String mail) {
        this.mail = mail;
    }

    
    @Column(name="name")
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="password")
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }




}


