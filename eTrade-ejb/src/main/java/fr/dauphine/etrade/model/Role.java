/***********************************************************************
 * Module:  Role.java
 * Author:  Sergiu
 * Purpose: Defines the Class Role
 ***********************************************************************/

package fr.dauphine.etrade.model;

import javax.persistence.Id;
import javax.persistence.Entity;

/** @pdOid 3df2d06e-3587-4513-ae4b-15a1040e0642 */
@Entity
public class Role implements java.io.Serializable {
   /** @pdOid 74ff8f22-e75a-467e-bcf5-1ea70fc21f1b */
   @Id
   private int id;
   /** @pdOid e9c73dcf-c64d-4b44-9cec-cfedbb6ce45a */
   private String libelle;
   
   /** @pdOid 529e4063-72d7-431e-ad99-1b4f8dca8cea */
   public int getId() {
      return id;
   }
   
   /** @param newId
    * @pdOid eeffd3f0-1f1f-4334-b527-8c7172dfaaab */
   public void setId(int newId) {
      id = newId;
   }
   
   /** @pdOid 9adb8b2b-0c02-4975-b86c-38e91e3d8e87 */
   public String getLibelle() {
      return libelle;
   }
   
   /** @param newLibelle
    * @pdOid d754b233-03d9-486f-becc-140b41817b8f */
   public void setLibelle(String newLibelle) {
      libelle = newLibelle;
   }

}