package fr.hibernate.dao;

import java.util.List;

import fr.hibernate.api.Connexion;
import fr.hibernate.metier.Commande;

public class DAOCommande {

	public int insert (Commande commande){
		try {
			Connexion.getInstance().insert(commande);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	public int delete (Commande commande){
		try{
			Connexion.getInstance().delete(commande);
			return 1;
		}
		catch (Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	public int update (Commande commande){
		try{
			Connexion.getInstance().update(commande);
			return 1;
		}
		catch (Exception e){
			e.printStackTrace();
			return 0;
		}

	}

	public static List<Commande> findAll (){
		return Connexion.getInstance().getAll(Commande.class);
	}

	public static Commande find (int idCommande){
		return Connexion.getInstance().find(Commande.class, idCommande);
	}


}
