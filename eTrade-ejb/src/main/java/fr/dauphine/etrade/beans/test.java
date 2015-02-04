package fr.dauphine.etrade.beans;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 * Session Bean implementation class test
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class test implements testRemote {

    /**
     * Default constructor. 
     */
    public test() {
        // TODO Auto-generated constructor stub
    }

}
