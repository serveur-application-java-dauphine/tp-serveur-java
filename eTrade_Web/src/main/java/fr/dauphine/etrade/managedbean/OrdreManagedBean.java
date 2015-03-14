package fr.dauphine.etrade.managedbean;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.dauphine.etrade.api.ServicesOrdre;
import fr.dauphine.etrade.api.ServicesPortefeuille;
import fr.dauphine.etrade.model.Ordre;
import fr.dauphine.etrade.model.Portefeuille;

@ManagedBean
@RequestScoped
public class OrdreManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ServicesOrdre so;
	
	@EJB
	private ServicesPortefeuille sp;
	
	private static Logger LOG = Logger.getLogger(OrdreManagedBean.class.getName());
	private Portefeuille p = sp.getPortefeuilleByUserEmail(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());
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
		return so.allDoneOrdres(p.getIdPortefeuille());
	}

	/**
	 * @return the list of pending orders
	 */
	public List<Ordre> getPendingOrders() {
		return so.allPendingOrdres(p.getIdPortefeuille());
	}
	
}