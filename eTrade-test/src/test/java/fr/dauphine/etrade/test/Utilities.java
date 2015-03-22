package fr.dauphine.etrade.test;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.ejb.client.ContextSelector;
import org.jboss.ejb.client.EJBClientConfiguration;
import org.jboss.ejb.client.EJBClientContext;
import org.jboss.ejb.client.PropertiesBasedEJBClientConfiguration;
import org.jboss.ejb.client.remoting.ConfigBasedEJBClientContextSelector;

public final class Utilities {
	
	private static InitialContext context;
	
	static {

		Properties clientProp = new Properties();
		clientProp.put("endpoint.name", "programmatic-client-endpoint");
		clientProp.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "false");
		clientProp.put("remote.connections", "default");
		clientProp.put("remote.connection.default.port", "4447");
		clientProp.put("remote.connection.default.host", "localhost"); 
		clientProp.put("remote.connection.default.username", "user");
		clientProp.put("remote.connection.default.password", "password");
		clientProp.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");
		clientProp.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", "false");
		clientProp.put("remote.connection.default.connect.options.org.xnio.Options.SASL_DISALLOWED_MECHANISMS", "JBOSS-LOCAL-USER");
		clientProp.put("remote.connection.default.connect.options.org.jboss.remoting3.RemotingOptions.HEARTBEAT_INTERVAL","60000");
		EJBClientConfiguration cc = new PropertiesBasedEJBClientConfiguration(clientProp);
		ContextSelector<EJBClientContext> selector = new ConfigBasedEJBClientContextSelector(cc);
		EJBClientContext.setSelector(selector);		
		
		Properties p = new Properties();
		p.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		try {
			context = new InitialContext(p);
		} catch (NamingException e) {
			e.printStackTrace();
			
		}
		
	}

	public static <T extends Object> T getServices(Class<T> classe){
		String classeName = classe.getSimpleName();
		System.out.println("getServices(): "+classeName);
		T t = null;
		try {
			t = classe.cast(context.lookup("ejb:eTrade_webEAR/eTrade_ejb/"+classe.getSimpleName()+"Bean!"+classe.getName()));
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return t;

	}

}
