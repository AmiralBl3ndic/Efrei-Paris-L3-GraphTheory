package tests;

import graph.*;
import graph.exceptions.InvalidGraphFileException;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphTheory {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String filename;
		String keepGoing;
		do {

			filename = "test-graphs/L3Int-4-";
			System.out.println("Enter the number of the graph file you wish to analyse :");
			filename += sc.next();
			filename += ".txt";

			try {
				Graph myGraph = new Graph(filename);

				System.out.println();
				System.out.println("=== VALUE MATRIX ===");
				System.out.println();
				System.out.println(myGraph.toString());
				System.out.println();
				myGraph.displayAdjacencyMatrix();
				System.out.println();
				System.out.println("Enter the start vertex for the shortest paths calculation :");
				int vertexId = sc.nextInt();

				if (myGraph.hasNegativeEdge()) {
					System.out.println();
					System.out.println("The graph has a negative edge, applying Bellman's algorithm.");
					System.out.println();
					myGraph.applyBellmanFord(vertexId);
				} else {
					int algorithmChoice;
					do {
						System.out.println();
						System.out.println("Please choose 1 for Dijkstra algorithm and 2 for Bellman algorithm :");
						algorithmChoice = sc.nextInt();
					} while (algorithmChoice != 1 && algorithmChoice != 2);
					if (algorithmChoice == 1) {
						myGraph.applyDijkstra(vertexId);
					} else {
						myGraph.applyBellmanFord(vertexId);
					}
				}
			} catch (FileNotFoundException | InvalidGraphFileException e) {
				e.printStackTrace();
			}
			System.out.println();
			System.out.println("Please enter 0 to quit and anything else to choose a new file :");
			keepGoing = sc.next();
		} while (!keepGoing.equals("0"));
	}

}
