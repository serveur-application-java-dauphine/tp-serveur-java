package fr.hibernate.dao;

import java.util.List;

import fr.hibernate.api.Connexion;

public class DAOGenerique {



	public int insert (Object object){
		try {
			Connexion.getInstance().insert(object);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	public int delete (Object object){
		try{
			Connexion.getInstance().delete(object);
			return 1;
		}
		catch (Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	public int update (Object object){
		try{
			Connexion.getInstance().update(object);
			return 1;
		}
		catch (Exception e){
			e.printStackTrace();
			return 0;
		}

	}

	public static <T> List<T> findAll (Class<T> classe){
		return Connexion.getInstance().getAll(classe);
	}

	public static <T> Object find (Class<T> classe, int idObject){
		return Connexion.getInstance().find(classe, idObject);
	}




}
