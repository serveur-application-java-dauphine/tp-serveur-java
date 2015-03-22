package fr.dauphine.etrade.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-20T15:09:38.612+0100")
@StaticMetamodel(Produit.class)
public class Produit_ {
	public static volatile SingularAttribute<Produit, Long> idProduit;
	public static volatile SingularAttribute<Produit, TypeProduit> typeProduit;
	public static volatile SingularAttribute<Produit, Societe> societe;
	public static volatile SingularAttribute<Produit, Date> maturite;
	public static volatile SingularAttribute<Produit, BigDecimal> coupon;
	public static volatile SingularAttribute<Produit, BigDecimal> taux;
	public static volatile SingularAttribute<Produit, BigDecimal> strike;
	public static volatile SingularAttribute<Produit, BigDecimal> volatilite;
	public static volatile SetAttribute<Produit, Ordre> ordres;
}
