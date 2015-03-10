package fr.dauphine.etrade.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-07T11:42:45.180+0100")
@StaticMetamodel(Transaction.class)
public class Transaction_ {
	public static volatile SingularAttribute<Transaction, Long> idTransaction;
	public static volatile SingularAttribute<Transaction, Ordre> ordreByIdOrderAchat;
	public static volatile SingularAttribute<Transaction, Ordre> ordreByIdOrderVente;
	public static volatile SingularAttribute<Transaction, BigDecimal> prix;
	public static volatile SingularAttribute<Transaction, Integer> quantite;
	public static volatile SingularAttribute<Transaction, Date> date;
}
