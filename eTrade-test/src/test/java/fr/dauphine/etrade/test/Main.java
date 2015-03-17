package fr.dauphine.etrade.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Main {
		
	public <T extends Object> T getServices(Class<T> classe){
		System.out.println("getServices(): "+classe.getName());
		return null;
		/*InitialContext initialContext = new InitialContext();
		ServicesCompte servicesCompte = (ServicesCompte) initialContext
		.lookup("java:global/eTrade_ejb/ServicesUtilisateurBean!"+serviceClass.getName());*/
	}
	
	public static InitialContext initialiserJBOSS() throws NamingException {
		Properties props = new Properties();
		
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        //props.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
        props.put(Context.PROVIDER_URL, "remote://localhost:4447");
        props.put(Context.SECURITY_PRINCIPAL, "etrade");
        props.put(Context.SECURITY_CREDENTIALS, "etradepwd");
        props.put("jboss.naming.client.ejb.context", true);
        return new InitialContext(props);
	}
	
}
