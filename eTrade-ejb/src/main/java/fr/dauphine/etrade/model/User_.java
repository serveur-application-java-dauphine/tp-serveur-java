package fr.dauphine.etrade.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-02-23T13:32:47.568+0100")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, String> mail;
}
