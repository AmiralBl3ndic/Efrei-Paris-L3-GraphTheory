package graph;

import java.util.ArrayList;

class Vertice {

	/**
	 * Id of the vertice
	 */
	private int id;

	/**
	 * Count of the number of {@link Vertice}s currently created
	 */
	private static int _number_vertices = 0;

	/**
	 * List of all edges that points towards the instance (from other instances)
	 */
	private ArrayList<Edge> inEdges = new ArrayList<>();

	/**
	 * List of all edges that point towards other instances (from this instance)
	 */
	private ArrayList<Edge> outEdges = new ArrayList<>();


	/**
	 * Create a new {@link Vertice} instance with its {@code id} depending
	 */
	public Vertice () {
		this.id = Vertice._number_vertices++;
	}


	/**
	 * Reset the class ids.
	 * Use only when creating a new graph, or at the end of a graph creation.
	 */
	static void resetIds () {
		Vertice._number_vertices = 0;
	}


	/**
	 * Add an incoming {@link Edge} to the instance
	 * @param edge Incoming {@link Edge} to add to the instance
	 */
	public void addInEdge (Edge edge) {
		this.inEdges.add(edge);
	}


	/**
	 * Add an outgoing {@link Edge} to the instance
	 * @param edge Outgoing {@link Edge} to add to the instance
	 */
	public void addOutEdge (Edge edge) {
		this.outEdges.add(edge);
	}


	/**
	 * Get the {@link Edge}s that points towards the instance
	 * @return {@link ArrayList} of {@link Edge}s that points towards the instance
	 */
	public ArrayList<Edge> getInEdges () {
		return this.inEdges;
	}


	/**
	 * Get the {@link Edge}s that are going from the instance to another instance of {@link Vertice}
	 * @return {@link ArrayList} of {@link Edge}s that points towards other instances and starts from the current instance
	 */
	public ArrayList<Edge> getOutEdge () {
		return this.outEdges;
	}


	/**
	 * Get the {@link ArrayList} of all {@link Edge}s that are related to the current instance
	 * @return {@link ArrayList} of all {@link Edge}s that are related to the current instance
	 */
	public ArrayList<Edge> getEdges () {

		// Initializing a new ArrayList containing all incoming edges
		ArrayList<Edge> edges = new ArrayList<>(this.inEdges);

		// Ad the remaining outgoing edges (that are not already incoming edges)
		for (Edge e : this.outEdges) {
			if (!edges.contains(e)) {  // Checking if the edge is not already in the list of edges
				edges.add(e);
			}
		}

		return edges;
	}
}
