package graph;

import graph.exceptions.InvalidGraphFileException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.FileReader;


public final class Graph {

	/**
	 * Number of vertices that the graph contains
	 */
	private int numberVertices;


	/**
	 * Array of vertices that the graph contains
	 */
	private ArrayList<Vertex> vertices;



	/**
	 * Create a graph based on its structure (contained in a text file)
	 * @param filePath Path to the file to open
	 * @throws FileNotFoundException If the passed file does not exist
	 * @throws InvalidGraphFileException If the passed file can not be used for instantiating a new {@link Graph}
	 */
	public Graph (String filePath) throws FileNotFoundException, InvalidGraphFileException {
		// Resetting the IDs of the Vertices
		Vertex.resetIds();  // Hence avoiding collision

		Scanner sc = new Scanner(new FileReader(filePath));  // Scanner on the file, throw a FileNotFound exception if needed


		// Attempt to read the first line (number of vertices in the graph)
		this.numberVertices = readNumberVertices(sc);

		this.vertices = new ArrayList<>();
		for (int i = 0; i < this.numberVertices; i++) {
			this.vertices.add(new Vertex());
		}


		// Going through all edges described by the file and adding them to the new instance
		while (sc.hasNextLine()) {
			this.addEdge(sc);
		}

		sc.close();
	}


	/**
	 * Add an edge to the graph, based on the {@link Scanner} on the building file
	 * @param sc {@link Scanner} on the building file
	 * @throws InvalidGraphFileException If a line does not have the right format
	 */
	private void addEdge (Scanner sc) throws InvalidGraphFileException {
		// Creating a new Scanner based on the line to parse
		try (Scanner sc2 = new Scanner(sc.nextLine())) {
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
		for (Vertex v : this.vertices) {
			// Print the ID of the current vertice
			System.out.print(String.format(" %d ║", v.getId()));

			// Check the successors of the current vertice
			for (Vertex vertex : this.vertices) {
				String link = v.hasSuccessor(vertex) ? " 1 " : "   ";
				System.out.print(link + "║");
			}

			System.out.print("\n");
		}
	}


	/**
	 * Get a {@link String} representation of the instance of the {@link Graph}
	 */
	@Override
	public String toString () {
		StringBuilder ret = new StringBuilder();

		ret.append("Adjacency matrix: \n");

		// First, we display the top line of the table
		ret.append("   ║");
		for (int i = 0; i < this.vertices.size(); i++) {
			ret.append(String.format(" %d ║", i));
		}
		ret.append("\n");

		// Then we display a separator line
		for (int i = -2; i < (this.vertices.size()/10) + 1; i++) {
			ret.append("═");
		}
		ret.append("║");
		for (int i = 0; i < this.vertices.size(); i++) {
			ret.append("═");
			for (int j = 0; j < (""+i).length(); j++) {
				ret.append("═");
			}
			ret.append("═║");
		}
		ret.append("\n");

		// Now, we check adjacency for each vertex
		for (Vertex v : this.vertices) {
			// Print the ID of the current vertice
			ret.append(String.format(" %d ║", v.getId()));

			// Check the successors of the current vertice
			for (Vertex vertex : this.vertices) {
				String link = v.hasSuccessor(vertex) ? " 1 " : "   ";
				ret.append(link).append("║");
			}

			ret.append("\n");
		}

		return ret.toString();
	}


	/**
	 * Check if the instance contains a {@link Edge} with a negative weight
	 * @return Whether or not the instance contains a {@link Edge} with a negative weight
	 */
	private boolean hasNegativeEdge () {
		// Check for all vertices of the graph
		for (Vertex v : this.vertices) {
			if (v.hasNegativeLink()) {
				return true;  // If the current Vertex contains an Edge with a negative weight
			}
		}

		return false;  // If none
	}


	/**
	 * Check whether or not the instance contains an absorbent cycle
	 * @return Whether or not the instance contains an absorbent cycle
	 */
	public boolean hasAbsorbentCycle () {
		for (Vertex v : this.vertices) {
			if (hasAbsorbentCycle(v, v,0, 0, new ArrayList<>())) {
				return true;
			}
		}

		return false;
	}


	/**
	 * Recursive method that checks whether or not there is an absorbent cycle starting from the given Vertex
	 * @param start Reference to the {@link Vertex} at the start of the potential cycle
	 * @param current Reference to the {@link Vertex} currently being analyzed
	 * @param cycleWeight Weight of the potential cycle being analyzed
	 * @param depth Number of iterations (call with {@code 0} to start)
	 * @param cleared {@link ArrayList} of {@link Vertex} acting as cache for already checked vertices
	 * @return Whether or not the {@code start} {@link Vertex} is the start of an absorbent cycle
	 */
	private boolean hasAbsorbentCycle (Vertex start, Vertex current, int cycleWeight, int depth, ArrayList<Vertex> cleared) {
		// If we looped back to the start, check the cycle weight
		if (current == start && depth != 0) {
			return cycleWeight < 0;
		}

		// Recursive call for each outgoing Edge of the current Vertex (optimized with caching)
		for (Edge e : current.getOutEdge()) {
			if (!cleared.contains(e.getEndVertex()) && hasAbsorbentCycle(start, e.getEndVertex(), cycleWeight + e.getWeight(), depth++, cleared)) {
				return true;
			}
		}

		// Add the current Vertex to the cache (already checked vertices)
		cleared.add(current);

		// By default, there is no absorbent cycle
		return false;
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
