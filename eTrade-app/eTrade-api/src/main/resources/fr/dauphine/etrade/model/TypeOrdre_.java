package fr.dauphine.etrade.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-20T15:09:38.639+0100")
@StaticMetamodel(TypeOrdre.class)
public class TypeOrdre_ {
	public static volatile SingularAttribute<TypeOrdre, Long> idTypeOrder;
	public static volatile SingularAttribute<TypeOrdre, String> libelle;
	public static volatile SetAttribute<TypeOrdre, Ordre> ordres;
}
