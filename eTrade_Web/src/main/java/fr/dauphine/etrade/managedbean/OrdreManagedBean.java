package fr.dauphine.etrade.managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import fr.dauphine.etrade.api.ServicesOrdre;
import fr.dauphine.etrade.api.ServicesProduit;
import fr.dauphine.etrade.api.ServicesSociete;
import fr.dauphine.etrade.model.DirectionOrdre;
import fr.dauphine.etrade.model.Ordre;
import fr.dauphine.etrade.model.Portefeuille;
import fr.dauphine.etrade.model.Produit;
import fr.dauphine.etrade.model.Societe;
import fr.dauphine.etrade.model.StatusOrdre;
import fr.dauphine.etrade.model.TypeOrdre;
import fr.dauphine.etrade.model.TypeProduit;
import fr.dauphine.etrade.model.Utilisateur;

@ManagedBean
@SessionScoped
public class OrdreManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Ordre ordre;
	
	//@ManagedProperty(value="#{sessionUserManagedBean}")
	//private SessionUserManagedBean session;
	FacesContext facesContext = FacesContext.getCurrentInstance();
	@SuppressWarnings("deprecation")
	Utilisateur utilisateur  = (Utilisateur) facesContext.getApplication().createValueBinding("#{sessionUserManagedBean.utilisateur}").getValue(facesContext);

	@EJB
	private ServicesOrdre so;
	
	@EJB
	private ServicesSociete ss;
	
	@EJB
	private ServicesProduit sp;
	
	private static Logger LOG = Logger.getLogger(OrdreManagedBean.class.getName());

	/**
	 * This method deletes an order from the database.
	 * It is called when an user wants to delete it.
	 * 
	 * TODO : Envisager un message de confirmation 
	 * avant de definitivement le supprimer
	 * 
	 * @param u
	 */
	

	public void supprimer(Ordre o){
		LOG.info("Deleting the order "+ o.getIdOrder());
		so.delOrdre(o);
	}
	
	
	/**
	 * @return the list of executed orders
	 */
	public List<Ordre> getExecutedOrders() {
		return so.allDoneOrdres(utilisateur.getPortefeuille().getIdPortefeuille());
	}

	/**
	 * @return the list of pending orders
	 */
	public List<Ordre> getPendingOrders() {
		List<Ordre> result = so.allPendingOrdres(utilisateur.getPortefeuille().getIdPortefeuille());
		return result;
	}
	
	public Ordre getOrdre() {
		if (ordre==null){
			ordre = new Ordre();
			ordre.setProduit(new Produit());
			ordre.getProduit().setSociete(new Societe());
			ordre.getProduit().setTypeProduit(new TypeProduit());
			ordre.setPortefeuille(new Portefeuille());
			ordre.setTypeOrdre(new TypeOrdre());
			ordre.setDirectionOrdre(new DirectionOrdre());
			ordre.setStatusOrdre(new StatusOrdre());
		}
        return ordre;
    }
	
	public List<DirectionOrdre> getListDirectionOrdres(){
		return so.getPossibleDirectionOrdre();
	}
	
	public List<TypeOrdre> getListTypeOrdres(){
		return so.getAllTypeOrdre();
	}
	
	/*
	 * Each time we change the Societe in the drop down list it changes
	 * in the ordre.produit	 
	 */
	public void changeSocieteListener(ValueChangeEvent event){
		ordre.getProduit().setSociete(ss.getSocieteById(Long.parseLong(event.getNewValue().toString())));
		ordre.getProduit().setTypeProduit(new TypeProduit());
	}
	
	public void changeTypeProduitListener(ValueChangeEvent event){
		ordre.getProduit().setTypeProduit(sp.getTypeProduitById(Long.parseLong(event.getNewValue().toString())));
	}
	
	public void changeTypeOrdreListener(ValueChangeEvent event){
		ordre.setTypeOrdre(so.getTypeOrdreById(Long.parseLong(event.getNewValue().toString())));
	}
	
	public void passerOrdre(){
		ordre.setProduit(sp.getProduitByTypeIdAndSocieteId(ordre.getProduit().getSociete().getIdSociete(), ordre.getProduit().getTypeProduit().getIdTypeProduit()));
		ordre.setStatusOrdre(so.getStatusOrdreByLibelle("Pending"));
		if(utilisateur.getPortefeuille()==null){
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("no_ordre.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			ordre.setPortefeuille(utilisateur.getPortefeuille());
			System.out.println("passerOrdre");
			so.addOrdre(ordre);			
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("succes_ordre.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ordre = null;
		
	}
	
	public void annulerOrdre(Ordre o){
		so.delOrdre(o);
	}
}
