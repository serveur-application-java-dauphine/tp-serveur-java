package fr.dauphine.etrade.test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.dauphine.etrade.api.Response;
import fr.dauphine.etrade.api.ResponseError;
import fr.dauphine.etrade.api.ResponseObject;
import fr.dauphine.etrade.api.ServicesUtilisateur;
import fr.dauphine.etrade.model.Role;
import fr.dauphine.etrade.model.Utilisateur;

public class ServicesUtilisateurTest {
	
	private final long ROLE_ADMINISTRATEUR_ID = 1;
	
	private Role roleAdmin;
	private Utilisateur u;
	private ServicesUtilisateur su;
	
	public ServicesUtilisateurTest(){
		roleAdmin = new Role();
		roleAdmin.setIdRole(ROLE_ADMINISTRATEUR_ID);
		su = Utilities.getServices(ServicesUtilisateur.class);
	}
	
	@Before
	public void before(){
		u = new Utilisateur(roleAdmin, "junit", "junit", System.currentTimeMillis()+"@junit.net", "junit", Calendar.getInstance().getTime(), "Test de l'adresse", "75000", "Paris", true); 
	}
	
	@Test
	public void sameEmail(){
		add();
		u.setIdUtilisateur(null);
		Response response = su.addUtilisateur(u);
		assertTrue(response instanceof ResponseError);
	}
	
	@Test
	public void getByEmail(){
		add();
		Utilisateur response = su.getUtilisateurByEmail(u.getEmail());
		assertEquals(response, u);
	}
	
	@Test
	public void allUtilisateurs(){
		List<Utilisateur> utilisateurs = su.allUtilisateurs();
		assertNotNull(utilisateurs);
	}
		
	@Test
	public void getById(){
		add();
		Utilisateur utilisateur = su.getUtilisateurById(u.getIdUtilisateur());
		assertEquals(utilisateur, u);
	}
	
	@Test
	public void add(){
		Response response = su.addUtilisateur(u);
		assertNotNull(response);
		assertFalse(response instanceof ResponseError);
		assertNotNull(((ResponseObject<Utilisateur>)response).object.getIdUtilisateur());
		u=((ResponseObject<Utilisateur>)response).object;
	}
	
	@Test
	public void del(){
		add();
		Response resp = su.delUtilisateur(u);
		assertNotNull(resp);
		assertFalse(resp instanceof ResponseError);
	}
	
	@Test
	public void update(){
		add();
		u.setFirstname("junitupdate");
		Response resp = su.updateUtilisateur(u);
		assertNotNull(resp);
		assertFalse(resp instanceof ResponseError);
		assertNotEquals(u, (ResponseObject<Utilisateur>)resp);
	}
	
}
