package fr.dauphine.etrade.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-07T11:42:45.195+0100")
@StaticMetamodel(TypeProduit.class)
public class TypeProduit_ {
	public static volatile SingularAttribute<TypeProduit, Long> idTypeProduit;
	public static volatile SingularAttribute<TypeProduit, String> libelle;
	public static volatile SetAttribute<TypeProduit, Produit> produits;
}
