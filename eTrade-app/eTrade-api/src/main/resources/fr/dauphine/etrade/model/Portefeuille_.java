package fr.dauphine.etrade.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-20T15:09:38.577+0100")
@StaticMetamodel(Portefeuille.class)
public class Portefeuille_ {
	public static volatile SingularAttribute<Portefeuille, Long> idPortefeuille;
	public static volatile SingularAttribute<Portefeuille, String> description;
	public static volatile SetAttribute<Portefeuille, Ordre> ordres;
	public static volatile SetAttribute<Portefeuille, TransactionBancaire> transactionBancaires;
	public static volatile SingularAttribute<Portefeuille, Utilisateur> utilisateur;
}
