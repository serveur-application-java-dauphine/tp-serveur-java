package fr.dauphine.etrade.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-07T11:42:45.180+0100")
@StaticMetamodel(TransactionBancaire.class)
public class TransactionBancaire_ {
	public static volatile SingularAttribute<TransactionBancaire, Long> idTrBancaire;
	public static volatile SingularAttribute<TransactionBancaire, TypeTransactionBancaire> typeTransactionBancaire;
	public static volatile SingularAttribute<TransactionBancaire, Portefeuille> portefeuille;
	public static volatile SingularAttribute<TransactionBancaire, BigDecimal> montant;
	public static volatile SingularAttribute<TransactionBancaire, Date> date;
}
