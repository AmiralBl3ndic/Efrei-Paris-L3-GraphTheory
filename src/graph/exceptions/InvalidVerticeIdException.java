package graph.exceptions;

public class InvalidVerticeIdException extends Exception {

	/**
	 * Create a new {@link InvalidVerticeIdException} with the passed value
	 * @param message Message of the {@link Exception}
	 * @param value Value that caused the Exception to be thrown
	 */
	public InvalidVerticeIdException (String message, int value) {
		super(String.format("Invalid vertice id: a vertice cannot have this value (%d)", value));
	}
}
