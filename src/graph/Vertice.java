package graph;

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
