package fr.dauphine.etrade.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.dauphine.etrade.api.ServicesUtilisateur;
import fr.dauphine.etrade.model.Role;
import fr.dauphine.etrade.model.Utilisateur;

//
//@RunWith(Suite.class)
//@SuiteClasses({ DictionaryTest.class, DictionaryParserTest.class })
//public class AllTests { //Empty class (introspection)
//
//}



public class UtilisateurRepositoryTest {
	
	@BeforeClass
	public static void testConnection() {
		Main.testConnectionBDD();
	}
	
	public Utilisateur createTestUtilisateur(ServicesUtilisateur rUser) {
		Utilisateur user = new Utilisateur(new Role("Administrateur"), "Dubernet", "Yoann", "dubernetyoann@laposte.net", "azerty", new Date(1991, 01, 01), "Test de l'adresse", "75000", "Paris", false); 
		
		return rUser.addUtilisateur(user);
	}
	
	public void removeTestUtilisateur(ServicesUtilisateur rUser, Utilisateur user) {
		rUser.delUtilisateur(user);
	}
	
	
	// CR
	@Test
	public void testAddRemoveGetByIDUtilisateur() throws NamingException {
		InitialContext ic = Main.initialiserJBOSS();
		//InitialContext ic = new InitialContext();
		
		String lookupStringUser = "eTrade-WebEAR/eTrade-ejb/ServicesUtilisateurBean!fr.dauphine.etrade.api.ServicesUtilisateur";

		ServicesUtilisateur rUser = (ServicesUtilisateur) ic.lookup(lookupStringUser);
		Utilisateur user = createTestUtilisateur(rUser); 
		
		Utilisateur fetchedUtilisateur = rUser.getUtilisateurById(user.getIdUtilisateur());
		assertTrue(fetchedUtilisateur.equals(user));
		removeTestUtilisateur(rUser, user);
		Utilisateur refetchedUtilisateur = rUser.getUtilisateurById(user.getIdUtilisateur());
		assertNull(refetchedUtilisateur);
		
		ic.close();
	}
	
	// U
	@Test
	public void testUpdateUtilisateur() throws NamingException {
        InitialContext ic = Main.initialiserJBOSS();

		String lookupStringUser = "eTrade-WebEAR/eTrade-ejb/ServicesUtilisateurBean!fr.dauphine.etrade.api.ServicesUtilisateur";
		ServicesUtilisateur rUser = (ServicesUtilisateur) ic.lookup(lookupStringUser);
		Utilisateur user = createTestUtilisateur(rUser); 
		
		user.setLastname("Nouveau nom");
		rUser.updateUtilisateur(user);
		Utilisateur fetchedUser = rUser.getUtilisateurById(user.getIdUtilisateur());
		assertTrue(fetchedUser.getLastname().equals("Nouveau nom"));
		
		removeTestUtilisateur(rUser, user);
		ic.close();
	}
	
	
	// D
	@Test public void testDeleteMembre() throws NamingException {
        InitialContext ic = Main.initialiserJBOSS();

        String lookupStringUser = "eTrade-WebEAR/eTrade-ejb/ServicesUtilisateurBean!fr.dauphine.etrade.api.ServicesUtilisateur";
		ServicesUtilisateur rUser = (ServicesUtilisateur) ic.lookup(lookupStringUser);
		Utilisateur user = createTestUtilisateur(rUser);
		
		assertNotNull(user);
		removeTestUtilisateur(rUser, user);
		assertNull(rUser.getUtilisateurById(user.getIdUtilisateur()));
		
		ic.close();
	}
	
}
