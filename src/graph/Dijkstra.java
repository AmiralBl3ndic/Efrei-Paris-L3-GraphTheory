package graph;

import java.util.*;

public class Dijkstra {

    private Graph g;
    private ArrayList<Vertex> v;
    private ArrayList<Integer> Distance;
    private int DistanceFromS;
    private ArrayList<Edge> currentVerticeEdges;
    
    Dijkstra() {

    }


	/**
	 * Constructor
	 * @param g
	 */
	Dijkstra(Graph g) {
        this.g = g;
        this.DistanceFromS = 0;

        if (g.hasNegativeEdge())
            System.out.println("We can't compute the Dijkstra's algorithm if there is negative edge !");
        else {
            int sizeG = g.getNbVertices();
            boolean[] vVisited = new boolean[sizeG];
            Arrays.fill(vVisited, false);
            computeShortestDistances(vVisited);
        }
    }


    public int DistanceFromSource(ArrayList<Edge> inEdges, int choice) {
        if (choice == -1) { //Initialization
            for (Edge e1 : inEdges) {
                Vertex sV = e1.getStartVertex();
                int weightFromId = sV.getId();
                if (sV.getId() == 0) {
                    this.DistanceFromS += weightFromId;  // x + 0 = x
                    this.Distance.set(0, this.DistanceFromS);
                    break;
                }
            }
        }else {
            Edge e2 = inEdges.get(choice);
            Vertex vertexE = e2.getStartVertex();
            int weightFromVE = vertexE.getId();
            if (vertexE.getId() == choice) {
                this.DistanceFromS += weightFromVE;
                this.Distance.set(choice, this.DistanceFromS);
            }
        }
        return DistanceFromS;
    }


    public void computeShortestDistances(boolean[] vVisted) {
        int nextVertex = 0;
        v = g.getVertices;
        Vertex v0 = v.get(0);
        ArrayList<Edge> inEdges = v[0].getInEdges;

        DistanceFromSource(inEdges, -1);

        // we visit every  vetice
        for (int i = 0 ; i < v.size() ; i++) {

            currentVerticeEdges = v.get(nextVertex).getEdges();

            for (int joinedEdge = 0; joinedEdge < currentVerticeEdges.size(); joinedEdge++) {
                Edge eNow = currentVerticeEdges.get(joinedEdge);
                Edge eNext = currentVerticeEdges.get(nextVertex);
                Vertice verticeEnStart = eNow.getStartVertex();
                Vertice verticeEnEnd = eNow.getEndVertex();

                if (verticeEnStart.getId == nextVertex) {
                    int neighbourIndex = verticeEnStart.getId;
                } else if (verticeEnStart.getId == nextVertex) {
                    int neighbourIndex = verticeEnStart.getId;
                }

                // only if not visited
                if ( !(vVisted[neighbourIndex]) ) {
                    int tentative = DistanceFromSource(currentVerticeEdges, nextVertex) + eNext.getLength();

                    if (tentative < DistanceFromSource(currentVerticeEdges, nextVertex)) {
                        Distance[neighbourIndex] = tentative;
                    }
                }
            }

            // all neighbours checked so Vertice visited
            vVisted[nextVertex] = true;

            // next Vertice must be with shortest distance
            nextVertex = getVerticesShortestDistanced();

        }
    }

    private int getVerticesShortestDistanced() {
        int storedVerticeIndex = 0;
        int storedDist = Integer.MAX_VALUE;

        ArrayList<Vertex> vSucc =  v.getSuccessors();

        for (int i = 0 ; i < vSucc.size() ; i++) {
            Vertex currentSucc = vSucc.get(i);
            ArrayList<Edge> edgeCurrSucc = currentSucc.getEdges();

            for (int joinedEdge = 0; joinedEdge < currentVerticeEdges.size(); joinedEdge++) {
                Edge eNow = edgeCurrSucc.get(joinedEdge);
                Edge eNext = edgeCurrSucc.get(nextVertex);
                Vertice verticeEnStart = eNow.getStartVertex();
                Vertice verticeEnEnd = eNow.getEndVertex();

                int neighbourIndex;
                if (verticeEnStart.getId == nextVertex || verticeStart.getId() == nextVertex) {
                    neighbourIndex = verticeEnStart.getId;
                } else if (verticeEnStart.getId == nextVertex) {
                    int neighbourIndex = verticeEnStart.getId;
                }

                int currentDist = DistanceFromSource(edgeCurrSucc, i);

                if ( (!vVisted[neighbourIndex]) && (currentDist < storedDist) ) {
                    storedDist = currentDist;
                    storedVerticeIndex = i;
                }
            }
        }

        return storedVerticeIndex;
    }

}