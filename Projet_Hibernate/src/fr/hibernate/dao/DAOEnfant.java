package fr.hibernate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.hibernate.metier.Commande;
import fr.hibernate.metier.Enfant;
import fr.hibernate.metier.Jouet;

public class DAOEnfant {
	
	private ArrayList<Enfant> cacheEnfants = new ArrayList<Enfant>();
	
	//Chargement au plus tôt
	public final static int mode_lent = 1;
	//Chargement au plus tard
	public final static int mode_rapide = 2;
	
	private final static String select_full = "SELECT * FROM commande,jouet,enfant "
			+ "WHERE commande.enfant_id=enfant.enfant_id AND commande.jouet_id=jouet.jouet_id";
private final static String select_fast = "SELECT * FROM enfant,commande WHERE commande.enfant_id=enfant.enfant_id";
	
	public int insert (Enfant e, Connection c){
		int result  = 0;
		try {
			PreparedStatement ps = c.prepareStatement("INSERT INTO enfant VALUES (nextval('enfant_enfant_id_seq'),?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, e.getNom());
			ps.setString(2, e.getPrenom());
			ps.setDate(3, e.getDdn());
			ps.setString(4, e.getAdresse());
			ps.setString(5, e.getVille());
			ps.setString(6, e.getCode_postal());
			ps.setString(7, e.getTel());
			ps.setString(8, e.getEmail());
			result = ps.executeUpdate();
			c.commit();
			// Pour récuperer la clé générée sous PostgreSQL
			ResultSet rset = ps.getGeneratedKeys();
        	if (rset.next())
        		e.setId(rset.getLong(1));
        	e.setCreated(true);
        	e.setPersist(true);
        	cacheEnfants.add(e);
        	System.out.println("(INSERT) L'enfant = "+e.toString()+" est persistant");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return result;
		
	}
	
	public int delete (Enfant e, Connection c){
		int result=0;
		if (!e.isCreated())
			return result;
		try {
			PreparedStatement ps = c.prepareStatement("DELETE FROM enfant WHERE enfant_id=?");
			ps.setLong(1, e.getId());
			result = ps.executeUpdate();
			c.commit();
			e.setPersist(false);
			e.setCreated(false);
			cacheEnfants.remove(e);
			System.out.println("(DELETE) L'enfant = "+e+" n'est plus persistant");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
		
	}
	
	public int update (Enfant e, Connection c){
		int result=0;
		try {
			PreparedStatement ps = c.prepareStatement("UPDATE enfant SET nom=?,prenom=?,date_naissance=?,adresse=?,ville=?,"
					+ "code_postal=?,telephone=?,email=? WHERE enfant_id=?");
			ps.setString(1, e.getNom());
			ps.setString(2, e.getPrenom());
			ps.setDate(3, e.getDdn());
			ps.setString(4, e.getAdresse());
			ps.setString(5, e.getVille());
			ps.setString(6, e.getCode_postal());
			ps.setString(7, e.getTel());
			ps.setString(8, e.getEmail());
			ps.setLong(9, e.getId());
			result = ps.executeUpdate();
			c.commit();
			e.setPersist(true);
			System.out.println("(UPDATE) L'enfant = "+e.toString()+" est persistant");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
		
	}
	
	
	public static ArrayList<Enfant> findAll (Connection c,int mode_chargement){
		ArrayList<Enfant> enfants = new ArrayList<Enfant>();
		try {
			ResultSet rs = c.createStatement().executeQuery(mode_chargement==mode_lent?select_full:select_fast);
				if (mode_chargement==mode_rapide){
					while (rs.next()){
						Enfant e = new Enfant();
						e.setId(rs.getLong(1));
						e.setNom(rs.getString(2));
						e.setPrenom(rs.getString(3));
						e.setDdn(rs.getDate(4));
						e.setAdresse(rs.getString(5));
						e.setVille(rs.getString(6));
						e.setCode_postal(rs.getString(7));
						e.setTel(rs.getString(8));
						e.setEmail(rs.getString(9));
						e.setCreated(true);
						e.setPersist(true);
						if (!contains(e,enfants))
							enfants.add(e);
						Commande co = new Commande();
						co.setPersist(true);
						co.setCreated(true);
						co.setEnfant(e);
						Jouet j = new Jouet();
						j.setId(rs.getLong(11));
						co.setDate_debut(rs.getDate(12));
						co.setDate_fin(rs.getDate(13));
						j.setPersist(true);
						j.setCreated(true);
					}
				}
		if (mode_chargement==mode_lent){
			while (rs.next()){
				Enfant e = new Enfant();
				e.setId(rs.getLong(1));
				e.setNom(rs.getString(9));
				e.setPrenom(rs.getString(10));
				e.setDdn(rs.getDate(11));
				e.setAdresse(rs.getString(12));
				e.setVille(rs.getString(13));
				e.setCode_postal(rs.getString(14));
				e.setTel(rs.getString(15));
				e.setEmail(rs.getString(16));
				e.setCreated(true);
				e.setPersist(true);
				if (!contains(e,enfants))
					enfants.add(e);
				Commande co = new Commande();
				co.setPersist(true);
				co.setCreated(true);
				co.setEnfant(e);
				Jouet j = new Jouet();
				j.setId(rs.getLong(2));
				co.setDate_debut(rs.getDate(3));
				co.setDate_fin(rs.getDate(4));
				j.setNom(rs.getString(6));
				j.setDescription(rs.getString(7));
				co.setJouet(j);
				j.addCommande(co);
				e.addCommande(co);
				j.setPersist(true);
				j.setCreated(true);
				
			}
		}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return enfants;
		
	}
	
	public void retrieveById(Enfant e, Connection c){
		
		try {
			PreparedStatement ps = c.prepareStatement("SELECT * FROM enfant WHERE enfant_id=?");
			ps.setLong(1, e.getId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				e.setId(rs.getLong(1));
				e.setNom(rs.getString(2));
				e.setPrenom(rs.getString(3));
				e.setDdn(rs.getDate(4));
				e.setAdresse(rs.getString(5));
				e.setVille(rs.getString(6));
				e.setCode_postal(rs.getString(7));
				e.setTel(rs.getString(8));
				e.setEmail(rs.getString(9));
				e.setCreated(true);
				e.setPersist(true);
			}
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	private static boolean contains (Enfant e, ArrayList<Enfant> enfants){
		for (Enfant enf : enfants){
			if (enf.equals(e)){
				e=enf;
				return true;
			}
				
		}
		return false;
		
	}
	

}
