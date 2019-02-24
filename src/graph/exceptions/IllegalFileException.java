package graph.exceptions;

/**
 * Exception to throw if a file is illegal in the given context
 */
public class IllegalFileException extends Exception {
	public IllegalFileException (String message) {
		super(message);
	}
}
