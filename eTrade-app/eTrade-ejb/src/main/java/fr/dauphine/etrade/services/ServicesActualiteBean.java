package fr.dauphine.etrade.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import fr.dauphine.etrade.api.ServicesActualite;
import fr.dauphine.etrade.model.Actualite;
import fr.dauphine.etrade.persist.Connexion;

@Remote(ServicesActualite.class)
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicesActualiteBean implements ServicesActualite {

  private static final Logger LOG = Logger.getLogger(ServicesActualiteBean.class.getName());

  @Override
  public Actualite getActualite(Long id) {
    return Connexion.getInstance().find(Actualite.class, id);
  }

  @Override
  public Actualite addActualite(Actualite a) {

    // Le nom du fichier est compos� de la date de cr�ation
    // + l'id de l'utilisateur l'ayant cr��
    // + l'id de la soci�t� � laquelle est affili� l'utilisateur.
    long creationDate = System.currentTimeMillis();
    String fileName = creationDate + "-" + a.getUtilisateur().getIdUtilisateur() + "-"
        + a.getSociete().getIdSociete() + ".txt";
    a.setFile(fileName);
    a.setDateCreation(new java.sql.Date(creationDate));

    LOG.info("Registering actualit� : " + fileName);

    BufferedOutputStream bos = null;
    try {
      bos = new BufferedOutputStream(new FileOutputStream(new File("/actualites/" + fileName)));
      bos.write(a.getContent().getBytes());
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

    Connexion.getInstance().insert(a);

    return a;
  }

  @Override
  public Actualite deleteActualite(Actualite a) {
    LOG.info("Deleting actualit� : " + a.getFile());

    // Suppression du fichier
    File f = new File("/actualites/" + a.getFile());
    f.delete();

    // Puis suppression de la localisation du xml en base
    Connexion.getInstance().delete(a);
    return a;
  }

}
