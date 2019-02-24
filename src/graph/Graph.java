package graph;

import graph.exceptions.InvalidGraphFileException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.FileReader;


public final class Graph {

	/**
	 * Number of {@link Vertice}s that the graph contains
	 */
	private int numberVertices;


	/**
	 * Array of {@link Vertice}s that the graph contains
	 */
	private ArrayList<Vertice> vertices;


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
		this.numberVertices = GraphBuilder.readNumberVertices(sc);

		this.vertices = new ArrayList<>(this.numberVertices);
	}

}
