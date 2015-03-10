package fr.dauphine.etrade.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-07T11:42:45.180+0100")
@StaticMetamodel(StatusOrdre.class)
public class StatusOrdre_ {
	public static volatile SingularAttribute<StatusOrdre, Long> idStatusOrder;
	public static volatile SingularAttribute<StatusOrdre, String> libelle;
	public static volatile SetAttribute<StatusOrdre, Ordre> ordres;
}
