package fr.dauphine.etrade.test;

import static org.junit.Assert.*;

import java.util.Date;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import fr.dauphine.etrade.api.ServicesUtilisateur;
import fr.dauphine.etrade.model.Role;
import fr.dauphine.etrade.model.Utilisateur;

public class UtilisateurRepositoryTest {
	
	@Before
	public void test(){
		try {
			InitialContext ctx = new InitialContext();
			System.out.println(ctx);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@EJB
	private ServicesUtilisateur su;
	
	public void removeTestUtilisateur(ServicesUtilisateur rUser, Utilisateur user) {
		rUser.delUtilisateur(user);
	}
	
	@Test
	public void addUser(){
		Utilisateur u1 = new Utilisateur(new Role("Administrateur"), "Dubernet", "Yoann", "dubernetyoann@laposte.net", "azerty", new Date(1991, 01, 01), "Test de l'adresse", "75000", "Paris", false); 
		su.addUtilisateur(u1);
		Utilisateur u2 = su.getUtilisateurByEmail("dubernetyoann@laposte.net");
		assertEquals(u1, u2);
	}
	
	
}
