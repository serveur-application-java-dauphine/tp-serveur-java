package fr.dauphine.etrade.api;


/**
 * This class implements a new local RepositoryException
 * to be able to identify this specified type of exception quickly.
 * 
 * @author Yoann
 *
 */
public class RepositoryException extends Exception {
	
	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public RepositoryException(){
		super();
	}
	
	public RepositoryException(String message){
		super(message);
	}
	
    public RepositoryException(Throwable cause) {
        super(cause);
    }
    
    protected RepositoryException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
	
	
}
