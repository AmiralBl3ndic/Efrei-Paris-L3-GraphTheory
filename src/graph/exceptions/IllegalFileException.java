package graph.exceptions;

/**
 * Exception to throw if a file is illegal in the given context
 */
public class IllegalFileException extends Exception {
	private static final long serialVersionUID = 1L;

	public IllegalFileException(String message) {
		super(message);
	}
}
