package fr.dauphine.etrade.managedbean;

import java.io.Serializable;

import java.sql.Timestamp;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import fr.dauphine.etrade.api.ServicesEnchere;
import fr.dauphine.etrade.api.ServicesOrdre;
import fr.dauphine.etrade.api.ServicesProduit;
import fr.dauphine.etrade.api.ServicesSociete;
import fr.dauphine.etrade.model.DirectionOrdre;
import fr.dauphine.etrade.model.Enchere;
import fr.dauphine.etrade.model.Ordre;
import fr.dauphine.etrade.model.Portefeuille;
import fr.dauphine.etrade.model.Produit;
import fr.dauphine.etrade.model.Societe;
import fr.dauphine.etrade.model.StatusOrdre;
import fr.dauphine.etrade.model.Transaction;
import fr.dauphine.etrade.model.TypeOrdre;
import fr.dauphine.etrade.model.TypeProduit;

@ManagedBean
@SessionScoped
public class OrdreManagedBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Ordre ordre;
	private List<Societe> listSocietes;
	private List<TypeOrdre> listTypeOrdres;
	private List<DirectionOrdre> listDirectionOrdres;

	private int duree = 0;


	@ManagedProperty(value="#{sessionUserManagedBean}")
	private SessionUserManagedBean sumb;

	public void setSumb(SessionUserManagedBean sumb) {
		this.sumb = sumb;
	}
	
	@EJB
	private ServicesOrdre so;

	@EJB
	private ServicesSociete ss;

	@EJB
	private ServicesProduit sp;

	@EJB
	private ServicesEnchere se;

	/**
	 * Liste pour le carnet d'ordres la partie achat (gauche)
	 */
	public List<Ordre> ordresAchatParProduit(long idProduit) {
		return so.ordresAchatParProduitId(idProduit);
	}

	/**
	 * Liste pour le carnet d'ordres la partie vente (droite)
	 */
	public List<Ordre> ordresVenteParProduit(long idProduit) {
		return so.ordresVenteParProduitId(idProduit);
	}

	/**
	 * @return the list of pending orders
	 */
	public List<Ordre> getPendingOrders() {
		return so.allPendingOrdres(sumb.getUtilisateur().getPortefeuille().getIdPortefeuille());
	}
	/**
	 * @return the list of done orders
	 */
	public List<Transaction> getDoneOrders() {
		return so.allDoneOrdres(sumb.getUtilisateur().getPortefeuille().getIdPortefeuille());
	}
	/**
	 * @return the ordre attribute of the Ordre class
	 */
	public Ordre getOrdre() {
		if (ordre == null) {
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

	/**
	 * Each time we change the Societe in the drop down list it changes in the
	 * ordre.produit
	 */
	public void changeSocieteListener(ValueChangeEvent event) {
		ordre.setProduit(new Produit());
		ordre.getProduit().setSociete(
				ss.getSocieteById(Long
						.parseLong(event.getNewValue().toString())));
	}

	public void changeTypeOrdreListener(ValueChangeEvent event) {
		ordre.setTypeOrdre(so.getTypeOrdreById(Long.parseLong(event
				.getNewValue().toString())));
	}

	/**
	 * Passing the order
	 */
	public void passerOrdre(){
		if(ordre.getTypeOrdre().getIdTypeOrder()==3){
			ordre.setQuantiteNonExecute(ordre.getQuantite());
			ordre.setPortefeuille(sumb.getUtilisateur().getPortefeuille());
			ordre.setProduit(sp.getProduitById(ordre.getProduit()
					.getIdProduit()));			
		    ordre.setStatusOrdre(so.getStatusOrdreByLibelle("Pending"));
		    so.addOrdre(ordre);
			Enchere enchere =new Enchere();
			Timestamp dateFin = new Timestamp(System.currentTimeMillis() + duree*3600000);
			enchere.setDateFin(dateFin);
			enchere.setPortefeuille(sumb.getUtilisateur().getPortefeuille());
			enchere.setMain(true);
			enchere.setOrdre(ordre);
			enchere.setPrix(ordre.getPrix());
			se.addEnchere(enchere);
			Utilities.redirect("succes_ordre.xhtml");
		} else {
			ordre.setQuantiteNonExecute(ordre.getQuantite());
			ordre.setPortefeuille(sumb.getUtilisateur().getPortefeuille());
			ordre.setProduit(sp.getProduitById(ordre.getProduit()
					.getIdProduit()));
			so.addOrdre(ordre);
			Utilities.redirect("succes_ordre.xhtml");
		}
		ordre = null;
	}

	/**
	 * This method deletes an order from the database. It is called when an user
	 * wants to delete it.
	 * 
	 * 
	 * @param u
	 */
	public void annulerOrdre(Ordre o) {
		so.delOrdre(o);
	}

	/**
	 * On instancie qu'une seule fois la listSocietes
	 * 
	 * @return listTypeOrdres
	 */
	public List<Societe> getListSocietes() {
		if (listSocietes == null) {
			listSocietes = ss.allSocietesAvecProduits();
		}
		return listSocietes;
	}

	public void setListSocietes(List<Societe> societes) {
		this.listSocietes = societes;
	}

	/**
	 * On instancie qu'une seule fois la listTypeOrdres
	 * 
	 * @return listTypeOrdres
	 */
	public List<TypeOrdre> getListTypeOrdres() {
		if (listTypeOrdres == null) {
			listTypeOrdres = so.getAllTypeOrdre();
		}
		return listTypeOrdres;
	}

	public void setListTypeOrdres(List<TypeOrdre> listTypeOrdres) {
		this.listTypeOrdres = listTypeOrdres;
	}

	/**
	 * On instancie qu'une seule fois la listDirectionOrdres
	 * 
	 * @return listTypeOrdres
	 */
	public List<DirectionOrdre> getListDirectionOrdres() {
		if (listDirectionOrdres == null) {
			listDirectionOrdres = so.getPossibleDirectionOrdre();
		}
		return listDirectionOrdres;
	}

	public void setListDirectionOrdres(List<DirectionOrdre> listDirectionOrdres) {
		this.listDirectionOrdres = listDirectionOrdres;
	}


	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public BigDecimal total(){
		BigDecimal total = new BigDecimal(0);
		List<Transaction> ordres = getDoneOrders();
		if (ordres!=null){
			for (Transaction t : ordres)
				total = total.add(t.getPrix().multiply(new BigDecimal(t.getQuantite())));
		}
		return total;

	}

}
