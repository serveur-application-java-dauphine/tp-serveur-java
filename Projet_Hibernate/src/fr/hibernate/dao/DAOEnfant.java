package fr.hibernate.dao;

import java.util.List;

import fr.hibernate.api.Connexion;
import fr.hibernate.metier.Enfant;

public class DAOEnfant {

	public boolean insert (Enfant enfant){
		try {
			Connexion.getInstance().insert(enfant);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean delete (Enfant enfant){
		try{
			Connexion.getInstance().delete(enfant);
			return true;
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean update (Enfant enfant){
		try{
			Connexion.getInstance().update(enfant);
			return true;
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}

	}

	public static List<Enfant> findAll (){
		return Connexion.getInstance().getAll(Enfant.class);
	}

	public static Enfant find (int idEnfant){
		return Connexion.getInstance().find(Enfant.class, idEnfant);
	}


}
