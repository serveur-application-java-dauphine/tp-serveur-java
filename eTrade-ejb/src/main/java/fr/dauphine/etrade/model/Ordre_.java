package fr.dauphine.etrade.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-07T11:42:45.149+0100")
@StaticMetamodel(Ordre.class)
public class Ordre_ {
	public static volatile SingularAttribute<Ordre, Long> idOrder;
	public static volatile SingularAttribute<Ordre, Produit> produit;
	public static volatile SingularAttribute<Ordre, DirectionOrdre> directionOrdre;
	public static volatile SingularAttribute<Ordre, StatusOrdre> statusOrdre;
	public static volatile SingularAttribute<Ordre, Portefeuille> portefeuille;
	public static volatile SingularAttribute<Ordre, TypeOrdre> typeOrdre;
	public static volatile SingularAttribute<Ordre, BigDecimal> prix;
	public static volatile SingularAttribute<Ordre, Integer> quantite;
	public static volatile SingularAttribute<Ordre, Date> date;
	public static volatile SetAttribute<Ordre, Transaction> transactionsForIdOrderVente;
	public static volatile SetAttribute<Ordre, Transaction> transactionsForIdOrderAchat;
}
