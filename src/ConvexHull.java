/*
Author: MacKenzie K. Cooper
Github: mackkcooper
*/


import java.util.Random;
import java.util.Vector;

abstract class ConvexHull {

    static Vertex getStart(Vector<Vertex> vector) {
        Vertex start = null;
        for(Vertex v : vector) {
            if(start == null)
                start = v;
            else if(start.y > v.y)
                start = v;
            else if(start.y == v.y && start.x > v.x)
                start = v;
        }
        return start;
    }

    private static double distance(Vertex a, Vertex b) {
        return Math.sqrt( ((a.x - b.x) * (a.x - b.x)) + ((a.y - b.y) * (a.y - b.y)) );
    }

    static int turn(Vertex a, Vertex b, Vertex c) {
        double cross = crossProduct(a,b,c);
        if(cross > 0)
            return 1; //left turn or counter clockwise
        if(cross < 0)
            return -1; //right turn or clockwise
        return 0; //straight or collinear
    }

    private static double crossProduct(Vertex a, Vertex b, Vertex c) {
        return ((b.x - a.x) * (c.y - a.y)) - ((b.y - a.y) * (c.x - a.x));
    }

    static boolean notSolvable(Graph toSolve) {
        if(toSolve == null)
            return false;
        if(toSolve.vertices == null)
            return false;
        return (toSolve.vertices.size() < 3);
    }

    static Vector<Edge> hullVerticesToEdges(Vector<Vertex> hullVertices) {
        if(hullVertices == null) return null;
        if(hullVertices.size() < 3) return null;
        int size = hullVertices.size();
        Vector<Edge> edges = new Vector<>();
        edges.add(new Edge(hullVertices.get(size-1),hullVertices.get(0)));
        for(int i = 1; i < size; ++i) {
            edges.add(new Edge(hullVertices.get(i-1),hullVertices.get(i)));
        }
        return edges;
    }

    static boolean verify(Graph g) {
        if(g == null || g.hullVertices == null || g.hullVertices.size() < 3) return false;
        if(g.edges == null || g.edges.size() < 3) return false;
        //verify closed polygon
        int size = g.edges.size();
        if(!g.edges.get(0).a.equals(g.edges.get(size-1).b)) return false;
        for(int i = 1; i < size; ++i) {
            if(!g.edges.get(i).a.equals(g.edges.get(i-1).b)) return false;
        }
        //verify all points are inside polygon
        for (Edge e : g.edges) {
            for (Vertex v : g.vertices) {
                if(e.a.equals(v) || e.b.equals(v)) continue;
                if(turn(e.b,e.a,v) > 0) return false;
            }
        }
        return true;
    }

    static Vector<Vertex> maxHullCase(long size) {
        long time = System.currentTimeMillis();
        Vector<Vertex> vertices = new Vector<>();
        double angle = Math.PI * 2 / size;
        for(long i = 0; i < size; ++i) {
            vertices.add(new Vertex(Math.cos(i*angle),Math.sin(i*angle)));
        }
        time = System.currentTimeMillis() - time;
        System.out.println("Data Production Time: " + time + " ms");
        return vertices;
    }

    static Vector<Vertex> minHullCase(long size) {
        long time = System.currentTimeMillis();
        Vector<Vertex> vertices = new Vector<>();
        Random random = new Random();
        double x, y, angle;
        for(long i = 0; i < size-3; ++i) {
            angle = random.nextDouble() * Math.PI * 2;
            x = Math.cos(angle) * random.nextDouble();
            y = Math.sin(angle) * random.nextDouble();
            vertices.add(new Vertex(x,y));
        }
        vertices.add(new Vertex(0,3));
        vertices.add(new Vertex(-2,-2));
        vertices.add(new Vertex(2,-2));
        time = System.currentTimeMillis() - time;
        System.out.println("Data Production Time: " + time + " ms");
        return vertices;
    }

    static Vector<Vertex> randomControlCase(long size, double percentOnHull) {
        long time = System.currentTimeMillis();
        Vector<Vertex> vertices = new Vector<>();
        Random random = new Random();
        double x, y, angle;
        long numOnHull = (long) Math.ceil(size*percentOnHull);
        long numInHull = size - numOnHull;
        for(long i = 0; i < numInHull; ++i) {
            angle = random.nextDouble() * Math.PI * 2;
            x = Math.cos(angle) * random.nextDouble() * .75;
            y = Math.sin(angle) * random.nextDouble() * .75;
            vertices.add(new Vertex(x,y));
        }
        for(long i = 0; i < numOnHull; ++i) {
            angle = random.nextDouble() * Math.PI * 2;
            x = Math.cos(angle);
            y = Math.sin(angle);
            vertices.add(new Vertex(x,y));
        }

        time = System.currentTimeMillis() - time;
        System.out.println("Data Production Time: " + time + " ms");
        return vertices;
    }

    static Vector<Vertex> randomCube(long size) {
        long time = System.currentTimeMillis();
        Vector<Vertex> vertices = new Vector<>();
        Random random = new Random();
        for (long i = 0; i < size; ++i)
            vertices.add(new Vertex(random.nextDouble()*size,random.nextDouble()*size));
        time = System.currentTimeMillis() - time;
        System.out.println("Data Production Time: " + time + " ms");
        return vertices;
    }

}
