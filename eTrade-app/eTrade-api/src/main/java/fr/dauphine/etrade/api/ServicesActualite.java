package fr.dauphine.etrade.api;

import javax.ejb.Remote;

import fr.dauphine.etrade.model.Actualite;

@Remote
public interface ServicesActualite {
  Actualite addActualite(Actualite a);

  Actualite getActualite(Long id);

  Actualite deleteActualite(Actualite a);

}
