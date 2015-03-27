package fr.hibernate.dao;

import java.util.List;

import fr.hibernate.api.Connexion;
import fr.hibernate.metier.Jouet;

public class DAOJouet {

	public boolean insert (Jouet jouet){
		try {
			Connexion.getInstance().insert(jouet);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean delete (Jouet jouet){
		try{
			Connexion.getInstance().delete(jouet);
			return true;
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public Jouet update (Jouet jouet){
		try{
			return Connexion.getInstance().update(jouet);
		}
		catch (Exception e){
			e.printStackTrace();
			return null;
		}

	}

	public static List<Jouet> findAll (){
		return Connexion.getInstance().getAll(Jouet.class);
	}

	public static Jouet find (int idJouet){
		return Connexion.getInstance().find(Jouet.class, idJouet);
	}


}
