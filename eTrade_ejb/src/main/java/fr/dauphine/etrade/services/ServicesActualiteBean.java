package fr.dauphine.etrade.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import fr.dauphine.etrade.api.ServicesActualite;
import fr.dauphine.etrade.model.Actualite;
import fr.dauphine.etrade.persit.Connexion;

@Remote(ServicesActualite.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesActualiteBean implements ServicesActualite {

	private static final Logger LOG = Logger.getLogger(ServicesActualiteBean.class.getName());

	@Override
	public Actualite getActualite(Long id) {
		Actualite act = Connexion.getInstance().find(Actualite.class, id);
		readContent(act);
		return act;
	}

	@Override
	public Actualite addActualite(Actualite a) {

		// Le nom du fichier est composé de la date de création
		// + l'id de l'utilisateur l'ayant créé
		// + l'id de la société à laquelle est affilié l'utilisateur.
		writeContent(a);

		Connexion.getInstance().insert(a);

		return a;
	}

	@Override
	public Actualite deleteActualite(Actualite a) {
		LOG.info("Deleting actualité : " + a.getFile());
		deleteContent(a);

		// Puis suppression de la localisation du xml en base
		Connexion.getInstance().delete(a);
		return a;
	}
	
	private void writeContent(Actualite act){
		FileOutputStream bos = null;
		try {
			bos = new FileOutputStream(getFile(act));
			bos.write(act.getContent().getBytes());
			bos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void readContent(Actualite act){
		FileInputStream bos = null;
		try {
			bos = new FileInputStream(getFile(act));
			int i;
			while ((i =bos.available())>0){
				byte[] temp = new byte[i];
				bos.read(temp);
				act.setContent(act.getContent()!=null?act.getContent():"" + new String(temp) );
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void deleteContent(Actualite act){
		getFile(act).delete();
	}
	
	private File getFile(Actualite a){
		String file = System.getProperty("user.home")+File.separatorChar/*+"eTrade"+File.separatorChar*/;
		if (a.getFile()!=null)
			file+=a.getFile();
		else{
			long creationDate = System.currentTimeMillis();
			String fileName = creationDate + "-" + a.getUtilisateur().getIdUtilisateur() + "-"
					+ a.getSociete().getIdSociete() + ".txt";
			a.setFile(fileName);
			a.setDateCreation(new java.sql.Date(creationDate));
			file+=fileName;
		}

		return new File(file);
			
	}

}
