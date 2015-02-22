package fr.dauphine.etrade.api;

public class RepositoryException extends Exception {
	public RepositoryException() {
	}

	public RepositoryException(String message) {
		super(message);
	}

	public RepositoryException(Throwable cause) {
		super(cause);
	}

	public RepositoryException(String message, Throwable cause) {
		super(message, cause);
	}
}
