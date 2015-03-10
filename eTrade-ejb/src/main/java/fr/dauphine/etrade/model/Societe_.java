package fr.dauphine.etrade.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-07T11:42:45.180+0100")
@StaticMetamodel(Societe.class)
public class Societe_ {
	public static volatile SingularAttribute<Societe, Long> idSociete;
	public static volatile SingularAttribute<Societe, String> name;
	public static volatile SetAttribute<Societe, Produit> produits;
	public static volatile SetAttribute<Societe, Utilisateur> utilisateurs;
	public static volatile SetAttribute<Societe, Actualite> actualites;
}
