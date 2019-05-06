package L3Int_4_graph;

/**
 * Represents a link between two vertices
 */
class Edge {

	/**
	 * Default weight for the {@link Edge}
	 */
	private final static int DEFAULT_WEIGHT = 1;

	/**
	 * The {@link Vertex} from which the {@link Edge} starts
	 */
	private Vertex startVertex;

	/**
	 * The {@link Vertex} to which the {@link Edge} goes
	 */
	private Vertex endVertex;

	/**
	 * The {@code weight} of the {@link Edge} (cost)
	 * After the instance has been instantiated, its {@code weight} cannot be changed
	 */
	private int weight = DEFAULT_WEIGHT;

	/**
	 * Counter of instances of edges
	 */
	private static int _number_edges = 0;

	/**
	 * ID of the instance
	 */
	private int id;


	/**
	 * Instantiate an {@link Edge} that links two vertices
	 * @param startVertex {@link Vertex} from which the {@link Edge} starts
	 * @param endVertex {@link Vertex} to which the {@link Edge} goes
	 */
	private Edge(Vertex startVertex, Vertex endVertex) {
		this.startVertex = startVertex;
		this.endVertex = endVertex;
		this.id = _number_edges++;
	}


	/**
	 * Instantiate an {@link Edge} that links two vertices
	 * @param startVertex {@link Vertex} from which the {@link Edge} starts
	 * @param endVertex {@link Vertex} to which the {@link Edge} goes
	 * @param weight Weight of the {@link Edge} (travel cost)
	 */
	Edge(Vertex startVertex, Vertex endVertex, int weight) {
		this(startVertex, endVertex);  // #CodeOptimization
		this.weight = weight;
	}


	/**
	 * Getter for the {@code weight} property (travel cost) of the instance
	 * @return Value of the {@code weight} property (travel cost) of the instance
	 */
	int getWeight() {
		return this.weight;
	}


	/**
	 * Getter for the {@code startVertice} property of the instance
	 * @return {@link Vertex} from which the {@link Edge} starts
	 */
	Vertex getStartVertex() {
		return this.startVertex;
	}


	/**
	 * Getter for the {@code endVertice} property of the instance
	 * @return {@link Vertex} to which the {@link Edge} goes
	 */
	Vertex getEndVertex() {
		return this.endVertex;
	}


	/**
	 * Get the unique ID of the instance
	 * @return Value of the {@code id} property of the instance
	 */
	int getId () {
		return this.id;
	}
}
