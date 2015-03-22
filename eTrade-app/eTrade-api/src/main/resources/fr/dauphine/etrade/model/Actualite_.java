package fr.dauphine.etrade.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-21T14:33:58.430+0100")
@StaticMetamodel(Actualite.class)
public class Actualite_ {
	public static volatile SingularAttribute<Actualite, Long> idActualite;
	public static volatile SingularAttribute<Actualite, Societe> societe;
	public static volatile SingularAttribute<Actualite, Utilisateur> utilisateur;
	public static volatile SingularAttribute<Actualite, String> file;
	public static volatile SingularAttribute<Actualite, String> titre;
	public static volatile SingularAttribute<Actualite, Date> dateCreation;
	public static volatile SingularAttribute<Actualite, Date> dateModification;
}
