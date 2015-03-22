package fr.dauphine.etrade.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-20T15:09:38.647+0100")
@StaticMetamodel(Utilisateur.class)
public class Utilisateur_ {
	public static volatile SingularAttribute<Utilisateur, Long> idUtilisateur;
	public static volatile SingularAttribute<Utilisateur, Societe> societe;
	public static volatile SingularAttribute<Utilisateur, Portefeuille> portefeuille;
	public static volatile SingularAttribute<Utilisateur, Role> role;
	public static volatile SingularAttribute<Utilisateur, String> lastname;
	public static volatile SingularAttribute<Utilisateur, String> firstname;
	public static volatile SingularAttribute<Utilisateur, String> email;
	public static volatile SingularAttribute<Utilisateur, String> password;
	public static volatile SingularAttribute<Utilisateur, Date> birthdate;
	public static volatile SingularAttribute<Utilisateur, String> adress;
	public static volatile SingularAttribute<Utilisateur, String> zipcode;
	public static volatile SingularAttribute<Utilisateur, String> city;
	public static volatile SingularAttribute<Utilisateur, Boolean> validRole;
}
