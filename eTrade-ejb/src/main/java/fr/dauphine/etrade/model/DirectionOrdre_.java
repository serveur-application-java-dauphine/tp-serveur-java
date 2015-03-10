package fr.dauphine.etrade.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-07T11:42:45.149+0100")
@StaticMetamodel(DirectionOrdre.class)
public class DirectionOrdre_ {
	public static volatile SingularAttribute<DirectionOrdre, Long> idDirectionOrdre;
	public static volatile SingularAttribute<DirectionOrdre, String> libelle;
	public static volatile SetAttribute<DirectionOrdre, Ordre> ordres;
}
