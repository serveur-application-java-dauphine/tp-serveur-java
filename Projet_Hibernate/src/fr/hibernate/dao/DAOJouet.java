package fr.hibernate.dao;

import java.util.List;

import fr.hibernate.api.Connexion;
import fr.hibernate.metier.Jouet;

public class DAOJouet {

	public int insert (Jouet jouet){
		try {
			Connexion.getInstance().insert(jouet);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	public int delete (Jouet jouet){
		try{
			Connexion.getInstance().delete(jouet);
			return 1;
		}
		catch (Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	public int update (Jouet jouet){
		try{
			Connexion.getInstance().update(jouet);
			return 1;
		}
		catch (Exception e){
			e.printStackTrace();
			return 0;
		}

	}

	public static List<Jouet> findAll (){
		return Connexion.getInstance().getAll(Jouet.class);
	}

	public static Jouet find (int idJouet){
		return Connexion.getInstance().find(Jouet.class, idJouet);
	}


}
