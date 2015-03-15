package fr.dauphine.etrade.test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

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
	
//	// U
//		@Test public void testUpdateUtilisateur() throws NamingException {
//	        InitialContext ic = Main.initialiserJBOSS();
//
//			String lookupStringUtilisateur = "amap-ear/amap-ejb/UtilisateurRepository!abracadacook.amap.api.IUtilisateurRepository";
//			IUtilisateurRepository rUtilisateur = (IUtilisateurRepository) ic.lookup(lookupStringUtilisateur);
//			Utilisateur Utilisateur = createTestUtilisateur(rUtilisateur); 
//			
//			Utilisateur.changerNom("Nouveau nom");
//			rUtilisateur.updateUtilisateur(Utilisateur);
//			Utilisateur fetchedUtilisateur = rUtilisateur.getUtilisateurById(Utilisateur.idUtilisateur());
//			assertTrue(fetchedUtilisateur.nom().equals("Nouveau nom"));
//			
//			removeTestUtilisateur(rUtilisateur, Utilisateur);
//			ic.close();
//		}
//		
//		// D
//			@Test public void testDeleteUtilisateur() throws NamingException {
//		        InitialContext ic = Main.initialiserJBOSS();
//
//				String lookupStringUtilisateur = "amap-ear/amap-ejb/UtilisateurRepository!abracadacook.amap.api.IUtilisateurRepository";
//				IUtilisateurRepository rUtilisateur = (IUtilisateurRepository) ic.lookup(lookupStringUtilisateur);
//				Utilisateur Utilisateur = createTestUtilisateur(rUtilisateur); 
//				
//				assertTrue(Utilisateur.estValide());
//				rUtilisateur.deleteUtilisateur(Utilisateur);
//				Utilisateur fetchedUtilisateur = rUtilisateur.getUtilisateurById(Utilisateur.getIdUtilisateur());
//				assertFalse(fetchedUtilisateur.estValide());
//				
//				removeTestUtilisateur(rUtilisateur, Utilisateur);
//				ic.close();
//			}
//	
	
	
}
