package fr.dauphine.etrade.api;

import java.util.List;

import javax.ejb.Remote;

import fr.dauphine.etrade.model.Role;

@Remote
public interface ServicesRole {
  List<Role> getRoles();

  Role getRole(int id);

  List<Role> getRolesLogin();

}
