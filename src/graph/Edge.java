package graph;

/**
 * Represents a link between two {@link Vertice}s
 */
class Edge {

	/**
	 * Default weight for the {@link Edge}
	 */
	public final static int DEFAULT_WEIGHT = 1;

	/**
	 * The {@link Vertice} from which the {@link Edge} starts
	 */
	private Vertice startVertice;

	/**
	 * The {@link Vertice} to which the {@link Edge} goes
	 */
	private Vertice endVertice;

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
	 * Instantiate an {@link Edge} that links two {@link Vertice}s
	 * @param startVertice {@link Vertice} from which the {@link Edge} starts
	 * @param endVertice {@link Vertice} to which the {@link Edge} goes
	 */
	public Edge (Vertice startVertice, Vertice endVertice) {
		this.startVertice = startVertice;
		this.endVertice = endVertice;
		this.id = _number_edges++;
	}


	/**
	 * Instantiate an {@link Edge} that links two {@link Vertice}s
	 * @param startVertice {@link Vertice} from which the {@link Edge} starts
	 * @param endVertice {@link Vertice} to which the {@link Edge} goes
	 * @param weight Weight of the {@link Edge} (travel cost)
	 */
	public Edge (Vertice startVertice, Vertice endVertice, int weight) {
		this(startVertice, endVertice);  // #CodeOptimization
		this.weight = weight;
	}


	/**
	 * Getter for the {@code weight} property (travel cost) of the instance
	 * @return Value of the {@code weight} property (travel cost) of the instance
	 */
	public int getWeight () {
		return this.weight;
	}


	/**
	 * Getter for the {@code startVertice} property of the instance
	 * @return {@link Vertice} from which the {@link Edge} starts
	 */
	public Vertice getStartVertice () {
		return this.startVertice;
	}


	/**
	 * Getter for the {@code endVertice} property of the instance
	 * @return {@link Vertice} to which the {@link Edge} goes
	 */
	public Vertice getEndVertice () {
		return this.endVertice;
	}


	/**
	 * Get the unique ID of the instance
	 * @return Value of the {@code id} property of the instance
	 */
	public int getId () {
		return this.id;
	}
}
