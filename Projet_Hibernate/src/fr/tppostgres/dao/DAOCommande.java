package fr.tppostgres.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.tppostgres.metier.Commande;
import fr.tppostgres.metier.Enfant;
import fr.tppostgres.metier.Jouet;

public class DAOCommande {
	
	private final static String select_full = "SELECT * FROM commande,jouet,enfant "
					+ "WHERE commande.enfant_id=enfant.enfant_id AND commande.jouet_id=jouet.jouet_id";
	private final static String select_fast = "SELECT * FROM commande";
	
	//Chargement au plus tôt
	public final static int mode_lent = 1;
	//Chargement au plus tard
	public final static int mode_rapide = 2;
	
	private ArrayList<Commande> cacheCommandes = new ArrayList<Commande>();
	
	public int insert (Commande co, Connection c){
		int result  = 0;
		
		//Créer les fils si ils ne le sont pas
		if (!co.getEnfant().isCreated())
			co.getEnfant().persister(c);
		if (!co.getJouet().isCreated())
			co.getJouet().persister(c);
		
		try {
			PreparedStatement ps = c.prepareStatement("INSERT INTO commande VALUES (?,?,?,?)");
			ps.setLong(1, co.getEnfant().getId());
			ps.setLong(2, co.getJouet().getId());
			ps.setDate(3, co.getDate_debut());
			ps.setDate(4, co.getDate_fin());
			result = ps.executeUpdate();
			c.commit();
        	co.setCreated(true);
        	co.setPersist(true);
        	cacheCommandes.add(co);
        	System.out.println("(INSERT) La commande = "+co+" est persistant");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return result;
		
	}
	
	public int delete (Commande commande, Connection c){
		int result=0;
		if (!commande.isCreated())
			return result;
		try {
			PreparedStatement ps = c.prepareStatement("DELETE FROM commande WHERE enfant_id=? AND jouet_id=?");
			ps.setLong(1, commande.getEnfant().getId());
			ps.setLong(2, commande.getJouet().getId());
			result = ps.executeUpdate();
			c.commit();
			commande.setPersist(false);
			commande.setCreated(false);
			cacheCommandes.remove(commande);
			System.out.println("(DELETE) La commande = "+commande+" n'est plus persistant");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
		
	}
	
	public int update (Commande co, Connection c){
		int result=0;
		//Persit des modifs des fils
		if (!co.getEnfant().isPersit())
			co.getEnfant().persister(c);
		if (!co.getJouet().isPersist())
			co.getJouet().persister(c);
		try {
			PreparedStatement ps = c.prepareStatement("UPDATE commande SET date_debut=?,date_fin=? WHERE enfant_id=? AND jouet_id=?");
			ps.setDate(1, co.getDate_debut());
			ps.setDate(2, co.getDate_fin());
			ps.setLong(3, co.getEnfant().getId());
			ps.setLong(4, co.getJouet().getId());
			result = ps.executeUpdate();
			c.commit();
			co.setPersist(true);
			System.out.println("(UPDATE) La commande = "+co+" est persistant");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
		
	}
	
	
	public static ArrayList<Commande> findAll (Connection c,int mode_chargement){
		ArrayList<Commande> commandes = new ArrayList<Commande>();
		try {
			ResultSet rs = c.createStatement().executeQuery(mode_chargement==mode_lent?select_full:select_fast);
			while (rs.next()){
				Commande co = new Commande();
				Enfant e = new Enfant();
				e.setId(rs.getLong(1));
				Jouet j = new Jouet();
				j.setId(rs.getLong(2));
				co.setEnfant(e);
				co.setJouet(j);
				co.setDate_debut(rs.getDate(3));
				co.setDate_fin(rs.getDate(4));
				
				if (mode_chargement==mode_lent){
					j.setNom(rs.getString(6));
					j.setDescription(rs.getString(7));
					e.setNom(rs.getString(9));
					e.setPrenom(rs.getString(10));
					e.setDdn(rs.getDate(11));
					e.setAdresse(rs.getString(12));
					e.setCode_postal(rs.getString(13));
					e.setTel(rs.getString(14));
					e.setEmail(rs.getString(15));
				}
				
				j.setCreated(true);
				j.setPersist(true);
				e.setCreated(true);
				e.setPersist(true);
				co.setCreated(true);
				co.setPersist(true);
				commandes.add(co);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commandes;
		
	}
	
	public void retrieveById(Commande co, Connection c,int mode_chargement){
		
		try {
			PreparedStatement ps = c.prepareStatement((mode_chargement==mode_lent?select_full:select_fast)+" WHERE enfant_id=? AND jouet_id=?");
			ps.setLong(1, co.getEnfant().getId());
			ps.setLong(2, co.getJouet().getId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				co.getEnfant().setId(rs.getLong(1));
				co.getJouet().setId(rs.getLong(2));
				co.setDate_debut(rs.getDate(3));
				co.setDate_fin(rs.getDate(4));
				
				if (mode_chargement==mode_lent){
					co.getJouet().setNom(rs.getString(6));
					co.getJouet().setDescription(rs.getString(7));
					co.getEnfant().setNom(rs.getString(9));
					co.getEnfant().setPrenom(rs.getString(10));
					co.getEnfant().setDdn(rs.getDate(11));
					co.getEnfant().setAdresse(rs.getString(12));
					co.getEnfant().setCode_postal(rs.getString(13));
					co.getEnfant().setTel(rs.getString(14));
					co.getEnfant().setEmail(rs.getString(15));
				}
				
				co.getJouet().setCreated(true);
				co.getJouet().setPersist(true);
				co.getEnfant().setCreated(true);
				co.getEnfant().setPersist(true);
				co.setCreated(true);
				co.setPersist(true);
			}
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	

}
