package graph;

class Vertice {

	/**
	 * Id of the vertice
	 */
	private int id;

	private static int _number_vertices = 0;


	/**
	 * Create a new {@link Vertice} instance with its {@code id} depending
	 */
	public Vertice () {
		this.id = Vertice._number_vertices++;
	}
}
