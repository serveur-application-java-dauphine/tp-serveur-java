/***********************************************************************
 * Module:  TypeProduit.java
 * Author:  Sergiu
 * Purpose: Defines the Class TypeProduit
 ***********************************************************************/

package fr.dauphine.etrade.model;

import javax.persistence.Id;
import javax.persistence.Entity;

/** @pdOid 948d3fbf-8720-4348-99d9-ac0714c70f66 */
public class TypeProduit {
   /** @pdOid 4d9a9f49-77b8-4b78-a145-36d0717d3da8 */
   @Id
   private int id;
   /** @pdOid 75b775f1-9bc3-4221-b58c-8657d502a0cc */
   private String libelle;
   
   /** @pdOid 6c449ee5-cc3e-4b5c-9204-b26bfece2927 */
   public int getId() {
      return id;
   }
   
   /** @param newId
    * @pdOid 04302ac2-613c-40b8-8a6b-f3c283eea058 */
   public void setId(int newId) {
      id = newId;
   }
   
   /** @pdOid 771bd1ae-a455-47c1-9080-2dd9a9559f38 */
   public String getLibelle() {
      return libelle;
   }
   
   /** @param newLibelle
    * @pdOid 4485ac3e-529d-40ed-8897-2ada79ed4e11 */
   public void setLibelle(String newLibelle) {
      libelle = newLibelle;
   }

}