package graph;


import graph.exceptions.InvalidGraphFileException;

import java.util.Scanner;

/**
 * Class that holds some static methods that are useful for building {@link Graph}s.
 * Use this class for all static methods that can be used for building a new {@link Graph} so that the code in the original {@link Graph} class stays clean.
 */
final class GraphBuilder {

	private GraphBuilder () {}  // Private constructor so that the class can not be instantiated


	/**
	 * Read the first line of the passed file, check the value and act consequently
	 * @param sc {@link Scanner} on the file to be read
	 * @return Number of vertices in the graph according to the file
	 * @throws InvalidGraphFileException If the file does not have the right format, structure or if data are not realistic (negative number of vertices)
	 */
	static int readNumberVertices (Scanner sc) throws InvalidGraphFileException {
		final int MINIMUM_NUMBER_OF_VERTICES = 1;

		if (sc.hasNextInt()) {  // Check if able to read an integer from the scanner
			int numberOfVertices = sc.nextInt();

			// Check read value
			if (numberOfVertices < MINIMUM_NUMBER_OF_VERTICES) {
				throw new InvalidGraphFileException("A graph must contain at least " + MINIMUM_NUMBER_OF_VERTICES + " vertices, but this file specifies " + numberOfVertices);
			}

			sc.nextLine();  // Escaping the end line character

			return numberOfVertices;  // Now safe to return

		} else {  // Throw an InvalidGraphFileException if not able to read
			throw new InvalidGraphFileException("Cannot read number of vertices from the passed file");
		}
	}
}
