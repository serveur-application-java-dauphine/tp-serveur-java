package fr.dauphine.etrade.managedbean;

import java.math.BigDecimal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class SessionPortefeuilleManagedBean {
	
	@ManagedProperty(value="#{ordreManagedBean}")
	private OrdreManagedBean omb;
	
	@ManagedProperty(value="#{sessionTransactionBancaireManagedBean}")
	private SessionTransactionBancaireManagedBean stbmb;
	
	public void setStbmb(SessionTransactionBancaireManagedBean stbmb){
		this.stbmb=stbmb;
	}
	
	public void setOmb(OrdreManagedBean omb){
		this.omb=omb;
	}
	
	public BigDecimal total(){
		return stbmb.total().add(omb.total());
	}

}
