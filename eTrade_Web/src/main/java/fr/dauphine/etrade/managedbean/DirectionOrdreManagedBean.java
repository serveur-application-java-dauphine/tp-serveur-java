package fr.dauphine.etrade.managedbean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import fr.dauphine.etrade.api.ServicesDirectionOrdre;
import fr.dauphine.etrade.model.DirectionOrdre;
@ManagedBean
@RequestScoped
public class DirectionOrdreManagedBean {

	private List<DirectionOrdre> TypesDirectionOrdre;
	
	@EJB
	private ServicesDirectionOrdre std;
	
	/**
	 * On instancie qu'une seule fois la listDirectionOrdres
	 * 
	 * @return listTypeOrdres
	 */
	public List<DirectionOrdre> getTypesDirectionOrdre() {
		if (TypesDirectionOrdre == null) {
			TypesDirectionOrdre = std.all();
		}
		return TypesDirectionOrdre;
	}

	public void setTypesDirectionOrdre(List<DirectionOrdre> TypesDirectionOrdre) {
		this.TypesDirectionOrdre = TypesDirectionOrdre;
	}
}
