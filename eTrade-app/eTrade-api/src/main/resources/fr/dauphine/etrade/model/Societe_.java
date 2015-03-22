package fr.dauphine.etrade.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-20T15:09:38.623+0100")
@StaticMetamodel(Societe.class)
public class Societe_ {
	public static volatile SingularAttribute<Societe, Long> idSociete;
	public static volatile SingularAttribute<Societe, String> name;
	public static volatile SingularAttribute<Societe, String> description;
	public static volatile SetAttribute<Societe, Produit> produits;
	public static volatile SetAttribute<Societe, Utilisateur> utilisateurs;
	public static volatile ListAttribute<Societe, Actualite> actualites;
}
