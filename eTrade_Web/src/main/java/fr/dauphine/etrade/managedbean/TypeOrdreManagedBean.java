package fr.dauphine.etrade.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import fr.dauphine.etrade.api.ServicesTypeOrdre;
import fr.dauphine.etrade.model.TypeOrdre;
@ManagedBean
@RequestScoped
public class TypeOrdreManagedBean implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private List<TypeOrdre> typesOrdre;
	
	@EJB
	private ServicesTypeOrdre sto;
	
	/**
	 * On instancie qu'une seule fois la listTypeOrdres
	 * 
	 * @return listTypeOrdres
	 */
	public List<TypeOrdre> getTypesOrdre() {
		if (typesOrdre == null) {
			typesOrdre = sto.all();
		}
		return typesOrdre;
	}

	public void setTypesOrdre(List<TypeOrdre> typesOrdre) {
		this.typesOrdre = typesOrdre;
	}
}
