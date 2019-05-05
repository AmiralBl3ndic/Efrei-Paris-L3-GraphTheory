package graph;

import java.util.*;

public class Dijkstra {

    private Graph g;
    private ArrayList<Vertex> v;
    private ArrayList<int> Distance;
    private int DistanceFromS;
    private ArrayList<Edge> currentVerticeEdges;
    
    Dijkstra() {

    }

    Dijkstra(Graph g) {
        this.g = g;
        DistanceFromS = 0;

        if (g.hasNegativeEdge)
            System.out.println("We can't compute the Dijkstra's algorithm if there are negative edge !");
        else {
            int sizeG = g.numberVertices;
            boolean vVisited[sizeG] = new boolean[];
            Arrays.fill(vVisited, false);
            computeShortestDistances(vVisited);
        }
    }

    public int DistanceFromSource(ArrayList<Edge> inEdges, int choice) {
        if (choice == -1) { //Initialization
            for (Edge e1 : inEdges) {
                Vertex sV = e1.startVertex;
                weightFromId = sV.id;
                if (sV.id == 0) {
                    DistanceFromS += weightFromId;
                    Distance[0] = DistanceFromS;
                    break;
                }
            }
        }else {
            e2 = inEdges.get(choice);
            vertexE = e2.startVertex;
            weightFromVE = vertexE.id;
            if (sV.id == choice) {
                DistanceFromS += weightFromId;
                Distance[choice] = DistanceFromS;
            }
        }
        return DistanceFromS;
    }

    public void computeShortestDistances(boolean[] vVisted) {
        int nextVertex = 0;
        v = g.vertices;
        Vertex v0 = v.get(0);
        ArrayList<Edge> inEdges = v[0].getInEdges;

        DistanceFromSource(inEdges, -1);

        // we visit every  vetice
        for (int i = 0 ; i < v.size() ; i++) {

            currentVerticeEdges = v.get(nextVertex).getEdges();

            for (int joinedEdge = 0; joinedEdge < currentVerticeEdges.size(); joinedEdge++) {
                Edge eNow = currentVerticeEdges.get(joinedEdge);
                Edge eNext = currentVerticeEdges.get(nextVertex);

                if (eNow.startVertex == nextVertex) {
                    int neighbourIndex = eNow.endVertex;
                } else if (eNow.endVertex == nextVertex) {
                    int neighbourIndex = eNow.startVertex;
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

                if (eNow.startVertex == nextVertex) {
                    int neighbourIndex = eNow.endVertex;
                } else if (eNow.endVertex == nextVertex) {
                    int neighbourIndex = eNow.startVertex;
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