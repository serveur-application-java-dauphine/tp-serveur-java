package fr.dauphine.etrade.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-07T11:42:45.195+0100")
@StaticMetamodel(TypeTransactionBancaire.class)
public class TypeTransactionBancaire_ {
	public static volatile SingularAttribute<TypeTransactionBancaire, Long> idTypeTrBancaire;
	public static volatile SingularAttribute<TypeTransactionBancaire, String> libelle;
	public static volatile SetAttribute<TypeTransactionBancaire, TransactionBancaire> transactionBancaires;
}
