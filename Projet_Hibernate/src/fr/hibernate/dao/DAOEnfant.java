package fr.hibernate.dao;

import java.util.List;

import fr.hibernate.api.Connexion;
import fr.hibernate.metier.Enfant;

public class DAOEnfant {

	public int insert (Enfant enfant){
		try {
			Connexion.getInstance().insert(enfant);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	public int delete (Enfant enfant){
		try{
			Connexion.getInstance().delete(enfant);
			return 1;
		}
		catch (Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	public int update (Enfant enfant){
		try{
			Connexion.getInstance().update(enfant);
			return 1;
		}
		catch (Exception e){
			e.printStackTrace();
			return 0;
		}

	}

	public static List<Enfant> findAll (){
		return Connexion.getInstance().getAll(Enfant.class);
	}

	public static Enfant find (int idEnfant){
		return Connexion.getInstance().find(Enfant.class, idEnfant);
	}


}
