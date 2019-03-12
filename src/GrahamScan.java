/*
Author: MacKenzie K. Cooper
Github: mackkcooper
*/

import java.util.Vector;

class GrahamScan {
    private Graph graph = null;
    private Vector <Vertex> examined = null;

    Graph solve(Graph toSolve) {
        if(toSolve.vertexCount < 3)
            return null;
        graph = new Graph(toSolve);
        examined = new Vector<>(100);
        Vertex start = getStart();
        start.display();


        return graph;
    }

    private Vertex getStart() {
        Vertex start = null;
        for(Vertex v : graph.vertices) {
            if(start == null)
                start = v;
            else if(start.y > v.y)
                start = v;
            else if(start.y == v.y && start.x > v.x)
                start = v;
        }
        return start;
    }

    private double polarAngle(Vertex a, Vertex b) {
        return Math.atan2(b.y-a.y,b.x-a.x);
    }
}
