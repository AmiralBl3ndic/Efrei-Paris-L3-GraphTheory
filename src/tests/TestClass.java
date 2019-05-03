package tests;

import graph.*;
import graph.exceptions.InvalidGraphFileException;

import java.io.FileNotFoundException;

public class TestClass {

	public static void main(String[] args) {
		try {
			Graph myGraph = new Graph("test-graphs/valid-1.txt");

			System.out.println(myGraph);

			myGraph.applyBellmanFord(myGraph.getVertex(0));


		} catch (FileNotFoundException | InvalidGraphFileException e) {
			e.printStackTrace();
		}
	}

}
