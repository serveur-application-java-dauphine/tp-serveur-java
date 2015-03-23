package fr.dauphine.etrade.managedbean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;

import fr.dauphine.etrade.api.ServicesEnchere;
import fr.dauphine.etrade.api.ServicesOrdre;
import fr.dauphine.etrade.api.ServicesPortefeuille;
import fr.dauphine.etrade.api.ServicesProduit;
import fr.dauphine.etrade.api.ServicesSociete;
import fr.dauphine.etrade.api.ServicesTypeOrdre;
import fr.dauphine.etrade.model.DirectionOrdre;
import fr.dauphine.etrade.model.Enchere;
import fr.dauphine.etrade.model.Ordre;
import fr.dauphine.etrade.model.Produit;
import fr.dauphine.etrade.model.Societe;
import fr.dauphine.etrade.model.StatusOrdre;
import fr.dauphine.etrade.model.Transaction;
import fr.dauphine.etrade.model.TypeOrdre;

@ManagedBean
@RequestScoped
public class OrdreManagedBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Ordre ordre;
	private int duree = 0;
	private List<Produit> actifs;


	@ManagedProperty(value="#{sessionUserManagedBean}")
	private SessionUserManagedBean sumb;

	public void setSumb(SessionUserManagedBean sumb) {
		this.sumb = sumb;
	}
	

	@ManagedProperty(value="#{sessionTransactionBancaireManagedBean}")
	private SessionTransactionBancaireManagedBean stbmb;

	@ManagedProperty(value="#{param.s}")
	private long idSociete;
	@ManagedProperty(value="#{param.p}")
	private long idProduit;
	/**
	 * @param idProduit the idProduit to set
	 */
	public void setIdProduit(long idProduit) {
		this.idProduit = idProduit;
	}


	@ManagedProperty(value="#{param.d}")
	private long idDirectionOrdre;
		
	/**
	 * @param idDirectionOrdre the idDirectionOrdre to set
	 */
	public void setIdDirectionOrdre(long idDirectionOrdre) {
		this.idDirectionOrdre = idDirectionOrdre;
	}


	@EJB
	private ServicesPortefeuille spo;

	/**
	 * @param stbmb the stbmb to set
	 */
	public void setStbmb(SessionTransactionBancaireManagedBean stbmb) {
		this.stbmb = stbmb;
	}
	
	public void setIdSociete(long idSociete){
		this.idSociete=idSociete;
	}

	
	@EJB
	private ServicesOrdre so;

	@EJB
	private ServicesSociete ss;

	@EJB
	private ServicesProduit sp;

	@EJB
	private ServicesEnchere se;
	
	@EJB
	private ServicesTypeOrdre sto;

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
			if (idProduit!=0)
				ordre.setProduit(sp.getProduitById(idProduit));
			else
				ordre.setProduit(new Produit());
			if (idSociete!=0)
				ordre.getProduit().setSociete(ss.getSocieteById(idSociete));
			else
				ordre.getProduit().setSociete(new Societe());
			
			ordre.setTypeOrdre(new TypeOrdre());
			DirectionOrdre dir = new DirectionOrdre();
			dir.setIdDirectionOrdre(idDirectionOrdre!=0?idDirectionOrdre:(long) 1);
			ordre.setDirectionOrdre(dir);
			ordre.setStatusOrdre(new StatusOrdre());
		}
		return ordre;
	}

	/**
	 * Each time we change the Societe in the drop down list it changes in the
	 * ordre.produit
	 */
	public void changeSocieteListener(ValueChangeEvent event) {
		if ((Long)event.getNewValue()==0){
			ordre.getProduit().setSociete(new Societe());
			return;
		}
		ordre.getProduit().getSociete().setIdSociete((Long)event.getNewValue());
	}
	
	public void changeProduitListener(ValueChangeEvent event) {
		ordre.getProduit().setIdProduit((Long) event.getNewValue());
	}

	public void changeTypeOrdreListener(ValueChangeEvent event) {
		ordre.getTypeOrdre().setIdTypeOrder((Long)event.getNewValue());
	}

	/**
	 * Passing the order
	 */
	public void passerOrdre(){
		if (ordre.getProduit().getIdProduit()==0){
			Utilities.addError("Vous devez sélectionner un produit", null);
			return;
		}
		if(ordre.getTypeOrdre().getIdTypeOrder()==3){
			ordre.setQuantiteNonExecute(ordre.getQuantite());
			ordre.setPortefeuille(sumb.getUtilisateur().getPortefeuille());
			ordre.setProduit(sp.getProduitById(ordre.getProduit()
					.getIdProduit()));
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
			if (ordre.getDirectionOrdre().getIdDirectionOrdre().equals(new Long(2)) && ordre.getQuantite()>quantiteActif(ordre.getQuantite())){
				Utilities.addError("Vous ne disposez que de "+quantiteActif(ordre.getProduit().getIdProduit())+ " "+ordre.getProduit().getLibelleProduit()  , null);
				return;
			}
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

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	private BigDecimal total(){
		BigDecimal total = new BigDecimal(0);
		List<Transaction> ordres = getDoneOrders();
		if (ordres!=null){
			for (Transaction t : ordres)
				total = total.add(t.getPrix().multiply(new BigDecimal(t.getQuantite())));
		}
		return total;

	}
	
	public BigDecimal totalPortefeuille(){
		return stbmb.total().add(total());
	}
	

	public List<Produit> getActifs() {
		if (actifs==null)
			actifs=sp.getActifs(sumb.getUtilisateur().getPortefeuille().getIdPortefeuille());
		return actifs;
	}


	public void setActifs(List<Produit> actifs) {
		this.actifs = actifs;
	}
	
	private Produit getActif(long idProduit){
		List<Produit> actifs = getActifs();
		for (Produit p : actifs){
			if (p.getIdProduit().equals(idProduit))
				return p;
		}
		return null;
	}

	public boolean containsActif(long idProduit){
		return getActif(idProduit)!=null;
	}
	
	public int quantiteActif(long idProduit){
		Produit p = getActif(idProduit);
		return p==null?0:p.getQuantite();
	}

}
