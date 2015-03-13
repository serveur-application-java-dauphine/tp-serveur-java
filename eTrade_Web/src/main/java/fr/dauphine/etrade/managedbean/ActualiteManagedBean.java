package fr.dauphine.etrade.managedbean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import fr.dauphine.etrade.api.ServicesActualite;
import fr.dauphine.etrade.model.Actualite;
import fr.dauphine.etrade.model.Role;
import fr.dauphine.etrade.model.Utilisateur;


@ManagedBean
@RequestScoped
public class ActualiteManagedBean {
	
	@EJB
	private ServicesActualite sa;
	
	private Actualite actualite;
	
	
	
	public Actualite getActualite(int id){
		return sa.getActualite(id);
	}
	
	public Actualite getActualite() {
		if (actualite==null){
			actualite = new Actualite();
		}
        return actualite;
    }
	
	
	
}
