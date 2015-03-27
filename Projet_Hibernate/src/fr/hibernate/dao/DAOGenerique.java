package fr.hibernate.dao;

import java.util.List;

import fr.hibernate.api.Connexion;

public final class DAOGenerique {
	
	public final static int insert (Object object){
		try {
			Connexion.getInstance().insert(object);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	public final static int delete (Object object){
		try{
			Connexion.getInstance().delete(object);
			return 1;
		}
		catch (Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	public final static int update (Object object){
		try{
			Connexion.getInstance().update(object);
			return 1;
		}
		catch (Exception e){
			e.printStackTrace();
			return 0;
		}

	}

	public final static <T> List<T> findAll (Class<T> classe){
		return Connexion.getInstance().getAll(classe);
	}

	public final static <T> Object find (Class<T> classe, int idObject){
		return Connexion.getInstance().find(classe, idObject);
	}




}
