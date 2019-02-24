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
		this.numberVertices = GraphBuilder.readNumberVertices(sc);

		this.vertices = new ArrayList<>(this.numberVertices);

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

}
