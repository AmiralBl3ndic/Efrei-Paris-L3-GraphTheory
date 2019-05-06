package L3Int_4_graph.exceptions;

public class InvalidVerticeIdException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Create a new {@link InvalidVerticeIdException} with the passed value
	 * 
	 * @param message Message of the {@link Exception}
	 * @param value   Value that caused the Exception to be thrown
	 */
	public InvalidVerticeIdException (String message, int value) {
		super(String.format("Invalid Vertex id: a Vertex cannot have this value (%d)", value));
	}
}
