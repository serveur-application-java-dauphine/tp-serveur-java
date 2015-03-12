package fr.dauphine.etrade.managedbean;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import fr.dauphine.etrade.api.ServicesSociete;
import fr.dauphine.etrade.model.Actualite;
import fr.dauphine.etrade.model.Societe;

@ManagedBean
@RequestScoped
public class SocieteManagedBean implements Serializable {

	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ServicesSociete ss;
	
	private static Logger LOG = Logger.getLogger(SocieteManagedBean.class.getName());
	
	
	public void createSociete(){
		Societe s = new Societe();
		LOG.info("Ajout d'une nouvelle société en base.");
		ss.addSociete(s);
	}
	
	public void removeSociete(Societe s){
		LOG.info("Suppression de la société : " +s.getName());
		ss.delSociete(s);
	}
	
	public void updateSociete(Societe s){
		LOG.info("Modification de la société : "+s.getName());
		ss.updateSociete(s);
	}
	
	
	
	/**
	 * 
	 * @return The list of all available societies
	 * 
	 * Can be called by an administrator and an investor profile.
	 */
	public List<Societe> getListSocietes(){
		return ss.allSocietes();
	}
	
	/**
	 * 
	 * @param id
	 * @return The society with the id in input
	 * 
	 * Can be called by all types of profiles.
	 */
	public Societe getSociete(int id){
		return ss.getSocieteById(id);
	}
	
	
	/**
	 * @return The list of available actualities on the website
	 * 
	 * Can be called by "Administrateur" profile.
	 */
	public List<Actualite> getAllActualites(){
		return ss.getAllActualites();
	}
	
	/**
	 * 
	 * @return The list of actualities for a society
	 * 
	 * Can be called by "Administrateur" & "Membre societe" profiles
	 */
	public List<Actualite> getListActualites(Societe s){
		return ss.getListActualites(s);
	}
	
	/**
	 * 
	 * @param id
	 * @return an actuality
	 * 
	 * Can be called by "Administrateur" & "Membre societe" profiles
	 */
	public Actualite getActualite(int id){
		return ss.getActualite(id);
	}
	
	
}
