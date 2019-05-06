package L3Int_4_graph.exceptions;


/**
 * Exception to be thrown when a vertex is not linked to another (but should be for the operation to complete)
 */
public class NotLinkedException extends Exception {
	final static String DEFAULT_MESSAGE = "The asked vertex is not linked";

	public NotLinkedException() {
		super(DEFAULT_MESSAGE);
	}

	public NotLinkedException(String message) {
		super(message);
	}
}
