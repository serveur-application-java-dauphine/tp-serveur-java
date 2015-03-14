package fr.dauphine.etrade.managedbean;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.inject.spi.Bean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.NoneScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import com.sun.jmx.snmp.Timestamp;

import sun.org.mozilla.javascript.internal.ast.NewExpression;
import fr.dauphine.etrade.api.ServicesOrdre;
import fr.dauphine.etrade.api.ServicesPortefeuille;
import fr.dauphine.etrade.api.ServicesProduit;
import fr.dauphine.etrade.api.ServicesSociete;
import fr.dauphine.etrade.model.DirectionOrdre;
import fr.dauphine.etrade.model.Ordre;
import fr.dauphine.etrade.model.Portefeuille;
import fr.dauphine.etrade.model.Produit;
import fr.dauphine.etrade.model.Role;
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
	
	//TODO @ManagedProperty(value="#{SessionUserManagedBean.utilisateur}")
	//private Utilisateur utilisateur;

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
		//TODO return so.allDoneOrdres(utilisateur.getPortefeuille().getIdPortefeuille());
		return null;
	}

	/**
	 * @return the list of pending orders
	 */
	public List<Ordre> getPendingOrders() {
		//TODO return so.allPendingOrdres(utilisateur.getPortefeuille().getIdPortefeuille());
		return null;
	}
	
	public Ordre getOrdre() {
		if (ordre==null){
			ordre = new Ordre();
			ordre.setProduit(new Produit());
			ordre.getProduit().setSociete(new Societe());
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
		ordre.setProduit(sp.getProduitByTypeIdAndSocieteId(ordre.getProduit().getTypeProduit().getIdTypeProduit(), ordre.getProduit().getSociete().getIdSociete()));
		ordre.setStatusOrdre(so.getStatusOrdreByLibelle("Pending"));
		//ordre.setPortefeuille(utilisateur.getPortefeuille());
		System.out.println("passerOrdre");
		so.addOrdre(ordre);
		
	}
}
