package fr.dauphine.etrade.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import fr.dauphine.etrade.api.ServicesActualite;
import fr.dauphine.etrade.model.Actualite;
import fr.dauphine.etrade.model.Societe;
import fr.dauphine.etrade.model.Utilisateur;
import fr.dauphine.etrade.persit.Connexion;

@Remote(ServicesActualite.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesActualiteBean implements ServicesActualite {
	
	private static final Logger LOG = Logger.getLogger(ServicesActualiteBean.class.getName());
	
	
	@Override
	public Actualite getActualite(int id){
		return Connexion.getInstance().find(Actualite.class, id);
	}

	@Override
	public Actualite addActualite(Actualite a) {
		
		// Le nom du fichier est composé de la date de création 
		//+ l'id de l'utilisateur l'ayant créé 
		//+ l'id de la société à laquelle est affilié l'utilisateur.
		long creationDate = System.currentTimeMillis();
		String fileName = creationDate + "-"; // TODO à récupérer en session + a.getUtilisateur().getIdUtilisateur() + "-" + a.getSociete().getIdSociete();
		a.setFile(fileName);
		a.setDate_creation(new java.sql.Date(creationDate));
		
		//TODO à enlever
		Societe s = new Societe();
		s.setIdSociete((long)1);
		Utilisateur u = new Utilisateur();
		u.setIdUtilisateur((long)8);
		
		a.setSociete(s);
		a.setUtilisateur(u);
		
		LOG.info("Registering actualité : "+fileName);
		
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream(fileName);
			fos.write(a.getContent().getBytes());
			fos.flush();
		} catch(IOException e){
			e.printStackTrace();
		} finally{
			try {
				if (fos!=null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//Persistence en base afin de récupérer l'id
		/*a = (Actualite)*/ Connexion.getInstance().insert(a);
		
		return a;
	}
	
	@Override
	public Actualite updateActualite(Actualite a) {
		LOG.info("Updating actualité : "+a.getFile());
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream(a.getFile());
			fos.write(a.getContent().getBytes());
		} catch(IOException e){
			e.printStackTrace();
		} finally{
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return a;
	}

	@Override
	public Actualite deleteActualite(Actualite a) {
		LOG.info("Deleting actualité : "+a.getFile());
		
		//Suppression du fichier 
		
		//Puis suppression de la localisation du xml en base
		Connexion.getInstance().delete(a);
		return a;
	}


	
	
	
}
