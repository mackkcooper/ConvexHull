/*
Author: MacKenzie K. Cooper
Github: mackkcooper
*/

import java.util.Vector;

abstract class JarvisMarch extends ConvexHull {

    static Graph solve(Graph toSolve) {
        long time = System.currentTimeMillis();
        if(notSolvable(toSolve)) return null;
        Graph graph = new Graph(toSolve);
        graph.hullVertices = new Vector<>(100);
        Vertex vertexOnHull = getStart(graph.vertices); //find guaranteed vertex on hull
        Vertex endVertex;
        int size = graph.vertices.size();
        do {
            graph.hullVertices.add(vertexOnHull);
            endVertex = graph.vertices.get(0);
            for(int j = 1; j < size; ++j) {
                if(endVertex == vertexOnHull || turn(vertexOnHull,endVertex,graph.vertices.get(j)) < 0)
                    endVertex = graph.vertices.get(j);
            }
            vertexOnHull = endVertex;
        } while(endVertex != graph.hullVertices.get(0));
        graph.edges = hullVerticesToEdges(graph.hullVertices);
        graph.solveTime = System.currentTimeMillis() - time;
        return graph;
    }
}
