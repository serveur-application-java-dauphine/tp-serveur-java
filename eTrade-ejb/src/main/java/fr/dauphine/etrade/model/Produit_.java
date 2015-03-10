package fr.dauphine.etrade.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-07T11:42:45.164+0100")
@StaticMetamodel(Produit.class)
public class Produit_ {
	public static volatile SingularAttribute<Produit, Long> idProduit;
	public static volatile SingularAttribute<Produit, TypeProduit> typeProduit;
	public static volatile SingularAttribute<Produit, Societe> societe;
	public static volatile SetAttribute<Produit, Ordre> ordres;
}
