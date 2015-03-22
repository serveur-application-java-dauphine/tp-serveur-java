package fr.dauphine.etrade.persist;

import java.util.logging.Level;
import java.util.logging.Logger;

import fr.dauphine.etrade.api.Response;
import fr.dauphine.etrade.api.ResponseError;
import fr.dauphine.etrade.api.ResponseObject;

public final class Utilities {

  public enum UtilitiesEnum {
    INSERT, DELETE, UPDATE
  };

  private static final Logger LOG = Logger.getLogger(Utilities.class.getName());

  public final static <T> Response doSimple(T object, UtilitiesEnum mode) {

    try {
      ResponseObject<T> response = new ResponseObject<T>();
      switch (mode) {
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
    } catch (Exception e) {
      ResponseError respError = new ResponseError();
      respError.error = e.getMessage();
      LOG.log(Level.SEVERE, respError.error);
      return respError;
    }
  }

}
