package fr.tppostgres.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.tppostgres.metier.Jouet;

public class DAOJouet {
	
	private ArrayList<Jouet> cacheJouets = new ArrayList<Jouet>();
	
	public int insert (Jouet j, Connection c){
		int result  = 0;
		try {
			PreparedStatement ps = c.prepareStatement("INSERT INTO jouet VALUES (nextval('jouet_jouet_id_seq'),?,?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, j.getNom());
			ps.setString(2, j.getDescription());
			result = ps.executeUpdate();
			c.commit();
			// Pour récuperer la clé générée sous PostgreSQL
			ResultSet rset = ps.getGeneratedKeys();
        	if (rset.next())
        		j.setId(rset.getLong(1));
        	j.setCreated(true);
        	j.setPersist(true);
        	cacheJouets.add(j);
        	System.out.println("(INSERT) Le jouet = "+j.toString()+" est persistant");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return result;
		
	}
	
	public int delete (Jouet j, Connection c){
		int result=0;
		if (!j.isCreated())
			return result;
		try {
			PreparedStatement ps = c.prepareStatement("DELETE FROM jouet WHERE jouet_id=?");
			ps.setLong(1, j.getId());
			result = ps.executeUpdate();
			c.commit();
			j.setPersist(false);
			j.setCreated(false);
			cacheJouets.remove(j);
			System.out.println("(DELETE) Le jouet = "+j+" n'est plus persistant");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
		
	}
	
	public int update (Jouet j, Connection c){
		int result=0;
		
		try {
			PreparedStatement ps = c.prepareStatement("UPDATE jouet SET nom=?,description=? WHERE jouet_id=?");
			ps.setString(1, j.getNom());
			ps.setString(2, j.getDescription());
			ps.setLong(3, j.getId());
			result = ps.executeUpdate();
			c.commit();
			j.setPersist(true);
			System.out.println("(UPDATE) Le jouet = "+j+" est persistant");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result;
		
	}
	
	
	public static ArrayList<Jouet> findAll (Connection c){
		ArrayList<Jouet> jouets = new ArrayList<Jouet>();
		try {
			ResultSet rs = c.createStatement().executeQuery("SELECT * FROM jouet");
			while (rs.next()){
				Jouet j = new Jouet();
				j.setId(rs.getLong(1));
				j.setNom(rs.getString(2));
				j.setDescription(rs.getString(3));
				j.setCreated(true);
				j.setPersist(true);
				jouets.add(j);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jouets;
		
	}
	
	public void retrieveById(Jouet j, Connection c){
		try {
			PreparedStatement ps = c.prepareStatement("SELECT * FROM jouet WHERE jouet_id=?");
			ps.setLong(1, j.getId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				j.setId(rs.getLong(1));
				j.setNom(rs.getString(2));
				j.setDescription(rs.getString(3));
				j.setCreated(true);
				j.setPersist(true);
			}
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}


	/*private getInCache (Jouet j){
		for (cacheJouets.)
		
	}*/
}
