package fr.dauphine.etrade.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Main {
	public static void testConnectionBDD() {
		String url = "jdbc:mysql://mysql.etrade.alwaysdata.net:3306/etrade_titres";
		String username = "etrade";
		String password = "azerty123";
		Connection connection = null;
		try {
		    System.out.println("Connecting database...");
		    connection = DriverManager.getConnection(url, username, password);
		    System.out.println("Database connected!");
		} catch (SQLException e) {
		    throw new RuntimeException("Cannot connect the database!", e);
		} finally {
		    System.out.println("Closing the connection.");
		    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
	}
	
	
	public static InitialContext initialiserJBOSS() throws NamingException {
		Properties props = new Properties();
		
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");//"org.jboss.naming.remote.client.InitialContextFactory");
        props.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
        props.put(Context.PROVIDER_URL, "localhost");//"remote://localhost:4447");
        props.put(Context.SECURITY_PRINCIPAL, "etrade");
        props.put(Context.SECURITY_CREDENTIALS, "etradepwd");
        props.put("jboss.naming.client.ejb.context", true);
        return new InitialContext(props);
	}
	
}
