package fr.dauphine.etrade.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import fr.dauphine.etrade.api.ServicesTypeProduit;
import fr.dauphine.etrade.model.TypeProduit;

@ManagedBean
@RequestScoped
public class TypeProduitManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<TypeProduit> typesProduit;
	private TypeProduit typeProduit;

	@EJB
	private ServicesTypeProduit stp;

	

	/**
	 * @return the typesProduit
	 */
	public List<TypeProduit> getTypesProduit() {
		if (typesProduit==null)
			typesProduit = stp.all();
		return typesProduit;
	}

	/**
	 * @param typesProduit the typesProduit to set
	 */
	public void setTypesProduit(List<TypeProduit> typesProduit) {
		this.typesProduit = typesProduit;
	}

	/**
	 * @return the typeProduit
	 */
	public TypeProduit getTypeProduit() {
		if (typeProduit==null)
			typeProduit = new TypeProduit();
		return typeProduit;
	}

	/**
	 * @param typeProduit the typeProduit to set
	 */
	public void setTypeProduit(TypeProduit typeProduit) {
		this.typeProduit = typeProduit;
	}

}
