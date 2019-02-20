package graph;

/**
 * Represents a link between two {@link Vertice}s
 */
public class Edge {

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
	 */
	private int weight = DEFAULT_WEIGHT;
}
