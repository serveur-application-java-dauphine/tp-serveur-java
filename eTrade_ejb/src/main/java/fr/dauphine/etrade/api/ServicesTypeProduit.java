package fr.dauphine.etrade.api;

import java.util.List;

import javax.ejb.Remote;

import fr.dauphine.etrade.model.TypeProduit;

@Remote
public interface ServicesTypeProduit {
  List<TypeProduit> all();
}
