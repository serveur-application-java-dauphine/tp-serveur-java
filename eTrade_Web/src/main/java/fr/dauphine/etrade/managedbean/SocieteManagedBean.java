package fr.dauphine.etrade.managedbean;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import fr.dauphine.etrade.api.ServicesSociete;
import fr.dauphine.etrade.api.ServicesUtilisateur;
import fr.dauphine.etrade.model.Actualite;
import fr.dauphine.etrade.model.Societe;
import fr.dauphine.etrade.model.TypeProduit;
import fr.dauphine.etrade.model.Utilisateur;

@ManagedBean
@RequestScoped
public class SocieteManagedBean implements Serializable {
	
	private Societe societe;
	private List<Actualite> actualites;
	private List<Societe> societes;

	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ServicesSociete ss;
	
	@EJB
	private ServicesUtilisateur su;
	
	
	FacesContext facesContext = FacesContext.getCurrentInstance();
	@SuppressWarnings("deprecation")
	Utilisateur utilisateur  = (Utilisateur) facesContext.getApplication().createValueBinding("#{sessionUserManagedBean.utilisateur}").getValue(facesContext);
	
	
	private static Logger LOG = Logger.getLogger(SocieteManagedBean.class.getName());
	
	
	/**
	 * Cette m�thode cr�e une soci�t�
	 * puis l'affecte automatiquement � l'utilisateur en ayant demand� la cr�ation.
	 */
	public void createSociete(){
		LOG.info("Ajout d'une nouvelle soci�t� en base : " + societe.getName());		
		Societe s = ss.addSociete(societe);
		
		utilisateur.setSociete(s);
		su.updateUtilisateur(utilisateur);
	}
	

//	public void removeSociete(Societe s){
//		LOG.info("Suppression de la soci�t� : " +s.getName());
//		ss.delSociete(s);
//	}

	
	public void updateSociete(Societe s){
		LOG.info("Modification de la soci�t� : "+s.getName());
		ss.updateSociete(s);
	}
	
	
	
	/**
	 * 
	 * @return The list of all available societies
	 * 
	 * Can be called by an administrator and an investor profile.
	 */
	
	public List<Societe> getListSocietesAvecProduits(){
		return ss.allSocietesAvecProduits();
	}
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
	public Societe getSociete(Long id){
		return ss.getSocieteById(id);
	}
	
	
	/**
	 * @return The list of available actualities on the website
	 * 
	 * Can be called by "Administrateur" profile.
	 */
	public List<Actualite> getAllActualites(){
		actualites = ss.getAllActualites();
		return actualites;
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

	public List<Actualite> getActualites() {
		return actualites;
	}

	public void setActualites(List<Actualite> actualites) {
		this.actualites = actualites;
	}

	public Societe getSociete() {
		if(societe==null)
			societe=new Societe();
		return societe;
	}

	public void setSociete(Societe societe) {
		this.societe = societe;
	}


	/**
	 * @return the societes
	 */
	public List<Societe> getSocietes() {
		if (societes==null)
			societes = ss.allSocietes();
		return societes;
	}


	/**
	 * @param societes the societes to set
	 */
	public void setSocietes(List<Societe> societes) {
		this.societes = societes;
	}

	
}
