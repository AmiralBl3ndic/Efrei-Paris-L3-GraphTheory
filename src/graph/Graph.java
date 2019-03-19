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
		// Resetting the IDs of the Vertices
		Vertice.resetIds();  // Hence avoiding collision

		Scanner sc = new Scanner(new FileReader(filePath));  // Scanner on the file, throw a FileNotFound exception if needed

		this.filePath = filePath;  // Saving the path


		// Attempt to read the first line (number of vertices in the graph)
		this.numberVertices = readNumberVertices(sc);

		this.vertices = new ArrayList<>();
		for (int i = 0; i < this.numberVertices; i++) {
			this.vertices.add(new Vertice());
		}


		// Going through all edges described by the file and adding them to the new instance
		while (sc.hasNextLine()) {
			this.addEdge(sc);
		}
	}


	/**
	 * Add an edge to the graph, based on the {@link Scanner} on the building file
	 * @param sc {@link Scanner} on the building file
	 * @throws InvalidGraphFileException If a line does not have the right format
	 */
	private void addEdge (Scanner sc) throws InvalidGraphFileException {
		Scanner sc2 = new Scanner(sc.nextLine());  // Creating a new Scanner based on the line to parse

		try {
			int from = sc2.nextInt();  // Reading the start vertex of the Edge
			int weight = sc2.nextInt();  // Reading the weight of the Edge
			int to = sc2.nextInt();  // Reading the end vertex of the Edge

			// Securing inputs
			if (from < 0 || from >= this.numberVertices || to < 0 || to >= this.numberVertices) {
				throw new InvalidGraphFileException("A line describing an Edge is faulty (wrong parameters): unable to build a Graph based on that file");
			}

			// If no error has been thrown here, we can add the Edge to the graph
			Edge edge = new Edge(this.vertices.get(from), this.vertices.get(to), weight);  // Actually creating the Edge to add
			this.vertices.get(from).addOutEdge(edge);
			this.vertices.get(to).addInEdge(edge);

		} catch (NoSuchElementException e) {
			throw new InvalidGraphFileException("A line describing an Edge is faulty (wrong structure): unable to build a Graph based on that file");
		}
	}


	/**
	 * Display the adjacency matrix of the current graph
	 */
	public void displayAdjacencyMatrix () {

		System.out.println("=== ADJACENCY MATRIX ===\n");

		// First, we display the top line of the table
		System.out.print("   ║");
		for (int i = 0; i < this.vertices.size(); i++) {
			System.out.print(String.format(" %d ║", i));
		}
		System.out.print("\n");

		// Then we display a separator line
		for (int i = -2; i < (this.vertices.size()/10) + 1; i++) {
			System.out.print("═");
		}
		System.out.print("║");
		for (int i = 0; i < this.vertices.size(); i++) {
			System.out.print("═");
			for (int j = 0; j < (""+i).length(); j++) {
				System.out.print("═");
			}
			System.out.print("═║");
		}
		System.out.print("\n");

		// Now, we check adjacency for each vertex
		for (Vertice v : this.vertices) {
			// Print the ID of the current vertice
			System.out.print(String.format(" %d ║", v.getId()));

			// Check the successors of the current vertice
			for (int i = 0; i < this.vertices.size(); i++) {
				String link = v.hasSuccessor(this.vertices.get(i)) ? " 1 " : "   ";
				System.out.print(link + "║");
			}

			System.out.print("\n");
		}
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

			sc.nextLine();  // Escaping the end line character

			return numberOfVertices;  // Now safe to return

		} else {  // Throw an InvalidGraphFileException if not able to read
			throw new InvalidGraphFileException("Cannot read number of vertices from the passed file");
		}
	}
}
