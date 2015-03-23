package fr.dauphine.etrade.persit;

import java.util.logging.Level;
import java.util.logging.Logger;

import fr.dauphine.etrade.api.Response;
import fr.dauphine.etrade.api.ResponseError;
import fr.dauphine.etrade.api.ResponseObject;

public final class Utilities {
	
	public static final int INSERT = 1;
	public static final int DELETE = 2;
	public static final int UPDATE = 3;
	
	private static final Logger LOG = Logger.getLogger(Utilities.class.getName());
		
	public final static <T> Response doSimple(T object,int mode){
		
		try{
			ResponseObject<T> response = new ResponseObject<T>();
			switch (mode){
			case INSERT:
				response.object = Connexion.getInstance().insert(object);
				break;
			case DELETE:
				response.object = Connexion.getInstance().delete(object);
				break;
			case UPDATE:
				response.object = Connexion.getInstance().update(object);
				break;
			}
			return response;
		}
		catch (Exception e){
			ResponseError respError = new ResponseError();
			respError.error = e.getMessage();
			LOG.log(Level.SEVERE,respError.error);
			return respError;
		}
	}

}
