package L3Int_4_graph;

import java.util.ArrayList;

import L3Int_4_graph.exceptions.NotLinkedException;

class Vertex {

	/**
	 * Id of the vertice
	 */
	private int id;

	/**
	 * Count of the number of vertices currently created
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
	 * Create a new {@link Vertex} instance with its {@code id} depending
	 */
	Vertex () {
		this.id = Vertex._number_vertices++;
	}


	/**
	 * Reset the class ids.
	 * Use only when creating a new L3Int_4_graph, or at the end of a L3Int_4_graph creation.
	 */
	static void resetIds () {
		Vertex._number_vertices = 0;
	}


	/**
	 * Get the id of the current instance
	 */
	int getId () {
		return this.id;
	}


	/**
	 * Add an incoming {@link Edge} to the instance
	 * @param edge Incoming {@link Edge} to add to the instance
	 */
	void addInEdge (Edge edge) {
		this.inEdges.add(edge);
	}


	/**
	 * Add an outgoing {@link Edge} to the instance
	 * @param edge Outgoing {@link Edge} to add to the instance
	 */
	void addOutEdge (Edge edge) {
		this.outEdges.add(edge);
	}


	/**
	 * Get the {@link Edge}s that points towards the instance
	 * @return {@link ArrayList} of {@link Edge}s that points towards the instance
	 */
	ArrayList<Edge> getInEdges () {
		return this.inEdges;
	}


	/**
	 * Get the {@link Edge}s that are going from the instance to another instance of {@link Vertex}
	 * @return {@link ArrayList} of {@link Edge}s that points towards other instances and starts from the current instance
	 */
	ArrayList<Edge> getOutEdge () {
		return this.outEdges;
	}


	/**
	 * Get the {@link ArrayList} of all {@link Edge}s that are related to the current instance
	 * @return {@link ArrayList} of all {@link Edge}s that are related to the current instance
	 */
	ArrayList<Edge> getEdges () {

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
	 * Get the {@link ArrayList} of all successor vertices of the current instance
	 * @return {@link ArrayList} of all successor vertices of the current instance
	 */
	ArrayList<Vertex> getSuccessors () {
		ArrayList<Vertex> successors = new ArrayList<>();

		// Running through the list of all outgoing edges
		return filterOutEdges(successors);
	}


	/**
	 * Get the {@link ArrayList} of all predecessor vertices of the current instance
	 * @return {@link ArrayList} of all predecessor vertices of the current instance
	 */
	private ArrayList<Vertex> getPredecessors() {
		ArrayList<Vertex> predecessors = new ArrayList<>();

		for (Edge e : this.inEdges) {
			if (!predecessors.contains(e.getStartVertex())) {
				predecessors.add(e.getStartVertex());
			}
		}

		return predecessors;
	}


	/**
	 * Get all the vertices linked to the current instance (predecessors or successors)
	 * @return {@link ArrayList} of {@link Vertex} that are are linked to the current instance (predecessors or successors)
	 */
	ArrayList<Vertex> getLinkedVertices () {
		ArrayList<Vertex> linkedVertices = new ArrayList<>(this.getPredecessors());

		return filterOutEdges(linkedVertices);  // Add the successors that are not already predecessors (looping edges)
	}


	/**
	 * Filter the out edges by adding only the ones that are not already part of the {@code vertices} parameter
	 * @param vertices {@link ArrayList} to add the vertices to
	 * @return Updated {@link ArrayList} of {@link Vertex}
	 */
	private ArrayList<Vertex> filterOutEdges(ArrayList<Vertex> vertices) {
		for (Edge e : this.outEdges) {
			if (!vertices.contains(e.getEndVertex())) {
				vertices.add(e.getEndVertex());
			}
		}

		return vertices;
	}


	/**
	 * Check if the current instance has another {@link Vertex} as a predecessor
	 * @param predecessor Vertex to check (as a predecessor)
	 * @return Whether or not the current instance has {@code predecessor} as a predecessor
	 */
	private boolean hasPredecessor(Vertex predecessor) {
		for (Edge e : this.inEdges) {
			if (e.getStartVertex().equals(predecessor)) {
				return true;
			}
		}

		return false;  // Default is false
	}


	/**
	 * Check if the current instance has another {@link Vertex} as a successor
	 * @param successor Vertex to check (as a successor)
	 * @return Whether or not the current instance has {@code successor} as a successor
	 */
	boolean hasSuccessor (Vertex successor) {
		for (Edge e : this.outEdges) {
			if (e.getEndVertex().equals(successor)) {  // Check if the passed Vertex is among the successors of the instance
				return true;
			}
		}

		return false;  // Default is false
	}


	/**
	 * Check whether or not the current instance is linked (successor or predecessor) to the {@link Vertex} passed as parameter
	 * @param vertex Vertex to check links for
	 * @return Whether or not the passed {@link Vertex} is linked to the current instance
	 */
	boolean isLinked (Vertex vertex) {
		return this.hasPredecessor(vertex) || this.hasSuccessor(vertex);
	}


	/**
	 * Check whether or not the instance has an Edge with a negative weight
	 * @return Whether or not the instance has an Edge with a negative weight
	 */
	boolean hasNegativeLink () {
		//  Check for incoming Edges
		for (Edge e : this.inEdges) {
			if (e.getWeight() < 0) {
				return true;
			}
		}

		// Check for outgoing Edges
		for (Edge e : this.outEdges) {
			if (e.getWeight() < 0) {
				return true;
			}
		}

		return false;  // At this point, we are sure there are none
	}


	/**
	 * Get the weight of the {@link Edge} to the passed {@link Vertex} (only if linked and outgoing)
	 * @param successor Successor {@link Vertex} to find the weight for
	 * @return Weight to the passed in successor {@link Vertex}
	 */
	int getWeightTo(Vertex successor) throws NotLinkedException {
		// Explore all outgoing edges
		for (Edge e : this.outEdges) {
			// If the good edge has been found
			if (e.getEndVertex() == successor) {
				return e.getWeight();
			}
		}

		throw new NotLinkedException("Vertex " + this.id + " is not directly linked to vertex " + successor.getId());
	}
}
