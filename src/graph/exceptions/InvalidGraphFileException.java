package graph.exceptions;

/**
 * Exception to throw when a graph file does not contain the right data, or has a structure problem
 */
public class InvalidGraphFileException extends IllegalFileException {
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_MESSAGE = "The passed Graph file contains a structural error: impossible to build a graph with this file";

	public InvalidGraphFileException () {
		super(DEFAULT_MESSAGE);
	}

	public InvalidGraphFileException (String message) {
		super(message);
	}
}
