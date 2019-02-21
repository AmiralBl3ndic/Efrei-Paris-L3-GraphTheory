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


	/**
	 * Get the {@link ArrayList} of all successor {@link Vertice}s of the current instance
	 * @return {@link ArrayList} of all successor {@link Vertice}s of the current instance
	 */
	public ArrayList<Vertice> getSuccessors () {
		ArrayList<Vertice> successors = new ArrayList<>();

		// Running through the list of all outgoing edges
		return filterOutEdges(successors);
	}


	/**
	 * Get the {@link ArrayList} of all predecessor {@link Vertice}s of the current instance
	 * @return {@link ArrayList} of all predecessor {@link Vertice}s of the current instance
	 */
	public ArrayList<Vertice> getPredecessors () {
		ArrayList<Vertice> predecessors = new ArrayList<>();

		for (Edge e : this.inEdges) {
			if (!predecessors.contains(e.getStartVertice())) {
				predecessors.add(e.getStartVertice());
			}
		}

		return predecessors;
	}


	/**
	 * Get all the {@link Vertice}s linked to the current instance (predecessors or successors)
	 * @return {@link ArrayList} of {@link Vertice}s that are are linked to the current instance (predecessors or successors)
	 */
	public ArrayList<Vertice> getLinkedVertices () {
		ArrayList<Vertice> linkedVertices = new ArrayList<>(this.getPredecessors());

		return filterOutEdges(linkedVertices);  // Add the successors that are not already predecessors (looping edges)
	}


	/**
	 * Filter the out edges by adding only the ones that are not already part of the {@code vertices} parameter
	 * @param vertices {@link ArrayList} to add the {@link Vertice}s to
	 * @return Updated {@link ArrayList} of {@link Vertice}s
	 */
	private ArrayList<Vertice> filterOutEdges(ArrayList<Vertice> vertices) {
		for (Edge e : this.outEdges) {
			if (!vertices.contains(e.getEndVertice())) {
				vertices.add(e.getEndVertice());
			}
		}

		return vertices;
	}


	/**
	 * Check if the current instance has another {@link Vertice} as a predecessor
	 * @param predecessor Vertice to check (as a predecessor)
	 * @return Whether or not the current instance has {@code predecessor} as a predecessor
	 */
	public boolean hasPredecessor (Vertice predecessor) {
		for (Edge e : this.inEdges) {
			if (e.getStartVertice().equals(predecessor)) {
				return true;
			}
		}

		return false;  // Default is false
	}


	/**
	 * Check if the current instance has another {@link Vertice} as a successor
	 * @param successor Vertice to check (as a successor)
	 * @return Whether or not the current instance has {@code successor} as a successor
	 */
	public boolean hasSuccessor (Vertice successor) {
		for (Edge e : this.outEdges) {
			if (e.getEndVertice().equals(successor)) {  // Check if the passed Vertice is among the successors of the instance
				return true;
			}
		}

		return false;  // Default is false
	}


	/**
	 * Check whether or not the current instance is linked (successor or predecessor) to the {@link Vertice} passed as parameter
	 * @param vertice Vertice to check links for
	 * @return Whether or not the passed {@link Vertice} is linked to the current instance
	 */
	public boolean isLinked (Vertice vertice) {
		return this.hasPredecessor(vertice) || this.hasSuccessor(vertice);
	}
}
