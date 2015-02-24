/***********************************************************************
 * Module:  TypeOrdre.java
 * Author:  Sergiu
 * Purpose: Defines the Class TypeOrdre
 ***********************************************************************/

package fr.dauphine.etrade.model;

import javax.persistence.Id;
import javax.persistence.Entity;
import java.util.*;

/** @pdOid 6ac120d5-a147-41c9-ad3d-1115ddcef207 */
@Entity
public class TypeOrdre {
   /** @pdOid f6437871-fb3b-4916-ba76-0df740577357 */
   @Id
   private int id;
   /** @pdOid a9d4570c-faa0-4d32-9524-aa804f93d7d2 */
   private String libelle;
   
   /** @pdOid 6117098d-6a53-4c00-adb3-98c679edc053 */
   public int getId() {
      return id;
   }
   
   /** @param newId
    * @pdOid d500d1ee-41ad-4e1a-8dfe-bda0fdedb66a */
   public void setId(int newId) {
      id = newId;
   }
   
   /** @pdOid 9401846c-8cfe-4bbe-9f6c-2375f89ef286 */
   public String getLibelle() {
      return libelle;
   }
   
   /** @param newLibelle
    * @pdOid 64183212-98a9-4b4a-aad1-23334901bec0 */
   public void setLibelle(String newLibelle) {
      libelle = newLibelle;
   }

}