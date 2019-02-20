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
}
