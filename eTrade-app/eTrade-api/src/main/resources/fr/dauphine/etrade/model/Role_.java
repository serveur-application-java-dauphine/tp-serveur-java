package fr.dauphine.etrade.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-20T15:09:38.618+0100")
@StaticMetamodel(Role.class)
public class Role_ {
	public static volatile SingularAttribute<Role, Long> idRole;
	public static volatile SingularAttribute<Role, String> libelle;
	public static volatile SetAttribute<Role, Utilisateur> utilisateurs;
	public static volatile SingularAttribute<Role, String> code;
}
