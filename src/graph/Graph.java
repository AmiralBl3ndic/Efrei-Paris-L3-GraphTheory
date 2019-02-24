package graph;

import graph.exceptions.InvalidGraphFileException;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileReader;


public final class Graph {

	/**
	 * Number of {@link Vertice}s that the graph contains
	 */
	private int numberVertices = 0;


	/**
	 * Array of {@link Vertice}s that the graph contains
	 */
	private Vertice[] vertices;


	/**
	 * Path to the file upon which the graph is built
	 */
	private final String filePath;


	/**
	 * Create a graph based on its structure (contained in a text file)
	 * @param filePath Path to the file to open
	 * @throws FileNotFoundException If the passed file does not exist
	 * @throws InvalidGraphFileException If the passed file can not be used for instantiating a new {@link Graph}
	 */
	public Graph (String filePath) throws FileNotFoundException, InvalidGraphFileException {
		Scanner sc = new Scanner(new FileReader(filePath));  // Scanner on the file, throw a FileNotFound exception if needed

		this.filePath = filePath;  // Saving the path


		// Attempt to read the first line (number of vertices in the graph)
		this.numberVertices = readNumberVertices(sc);

	}


	/**
	 * Read the first line of the passed file, check the value and act consequently
	 * @param sc {@link Scanner} on the file to be read
	 * @return Number of vertices in the graph according to the file
	 * @throws InvalidGraphFileException If the file does not have the right format, structure or if data are not realistic (negative number of vertices)
	 */
	private static int readNumberVertices (Scanner sc) throws InvalidGraphFileException {
		final int MINIMUM_NUMBER_OF_VERTICES = 1;

		if (sc.hasNextInt()) {  // Check if able to read an integer from the scanner
			int numberOfVertices = sc.nextInt();

			// Check read value
			if (numberOfVertices < MINIMUM_NUMBER_OF_VERTICES) {
				throw new InvalidGraphFileException("A graph must contain at least " + MINIMUM_NUMBER_OF_VERTICES + " vertices, but this file specifies " + numberOfVertices);
			}

			return numberOfVertices;  // Now safe to return

		} else {  // Throw an InvalidGraphFileException if not able to read
			throw new InvalidGraphFileException("Cannot read number of vertices from the passed file");
		}
	}
}
