/*
import java.util.Vector;

abstract class ChanScan extends ConvexHull {

    static Graph solve(Graph toSolve) {
        //check for solvability
        if(notSolvable(toSolve)) return null;
        Graph solution;
        int m;
        int size = toSolve.vertices.size();
        //run partialHull on increasing m until m >= h and solution returned
        for(int t = 0; t < Math.log(Math.log(size)); ++t) {
            m = Math.min((int) Math.pow(2,Math.pow(2,t)),size); //squaring scheme gives O(n log h)-complexity
            solution = partialHull(toSolve,m);
            if(solution != null)
                return solution;
        }
        return null;
    }

    private static Graph partialHull(Graph toSolve, int m) {
        Graph graph = new Graph(toSolve); //duplicate toSolve
        int size = graph.vertices.size();
        int k = (int) Math.ceil((double)size/(double)m); // k = ceil(size/m)
        Vector<Vector<Vertex>> vertexGroups = splitVertices(graph.vertices,k); //split vertices
        Vector<Vector<Vertex>> hullGroups = new Vector<>(); //sub-solutions go here
        Graph subHull;
        //produce sub-solutions from vertices
        for(int i = 0; i < k; ++i) {
            subHull = GrahamScan.solve(new Graph(vertexGroups.get(i)));
            if(subHull == null) return null;
            hullGroups.add(Vertex.copyVector(subHull.hullVertices));
        }
        graph = solveHullGroups(graph,hullGroups,m);
        if(graph == null) return null;
        graph.edges = hullVerticesToEdges(graph.hullVertices);
        return graph;
    }

    private static Graph solveHullGroups(Graph graph, Vector<Vector<Vertex>> hullGroups, int m) {
        int k = hullGroups.size();
        graph.hullVertices = new Vector<>();
        Vertex start = getStart(graph.vertices);
        graph.addHullVertex(start);
        Vertex best = null;
        Vertex prospective = null;
        for(int i = 1; i < m; ++i) { //for each potential hull item
            for(int j = 0; j < k; ++j) { //for each sub hull
                if(best == null) {
                    best = getRightTangent(start,hullGroups.get(j));
                }
            }
            graph.addHullVertex(a);
        }
        return null;
    }

    private static Vertex getRightTangent(Vertex v, Vector<Vertex> p) {
        if(v == null || p == null) return null;
        int size = p.size();
        if( turn(v,p.get(1),p.get(0)) < 0 && !(turn(v,p.get(size-1),p.get(0)) > 0) )
            return p.get(0);
        int a = 0;
        int b = size;
        int c;
        boolean upA,downC;
        while(true) {
            c = (a+b)/2;
            downC = turn(v,p.get(c+1),p.get(c)) < 0;
            if( downC && !(turn(v,p.get(c-1),p.get(c)) > 0) )
                return p.get(c);

            upA = turn(v,p.get(a+1),p.get(a)) > 0;
            if(upA) {
                if(downC) {
                    b = c;
                } else {
                    if(turn(v,p.get(a),p.get(c)) > 0) {
                        b = c;
                    } else {
                        a = c;
                    }
                }
            } else {
                if(!downC) {
                    a = c;
                } else {
                    if(turn(v,p.get(a),p.get(c)) < 0) {
                        b = c;
                    } else {
                        a = c;
                    }
                }
            }
        }
    }

    private static Vector<Vector<Vertex>> splitVertices(Vector<Vertex> vector, int k) {
        if(vector == null) return null;
        Vector<Vector<Vertex>> array = new Vector<>();
        for(int i = 0; i < k; ++i)
            array.add(new Vector<>());
        int size = vector.size();
        for(int i = 0; i < size; ++i) {
            array.get(i%k).add(new Vertex(vector.get(i)));
        }
        return array;
    }
}
*/