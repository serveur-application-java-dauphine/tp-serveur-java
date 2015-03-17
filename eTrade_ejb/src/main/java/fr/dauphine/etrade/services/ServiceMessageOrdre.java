package fr.dauphine.etrade.services;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Message-Driven Bean implementation class for: ServiceMessageOrdre
 */
//@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class ServiceMessageOrdre implements MessageListener {

  /**
   * Default constructor.
   */
  public ServiceMessageOrdre() {

  }

  /**
   * @see MessageListener#onMessage(Message)
   */
  public void onMessage(Message message) {

  }

}
