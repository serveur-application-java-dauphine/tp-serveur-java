package fr.dauphine.etrade.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-02-22T00:27:47.804+0100")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> userName;
	public static volatile SingularAttribute<User, String> mailAddress;
	public static volatile SingularAttribute<User, String> password;
}
