/*
Author: MacKenzie K. Cooper
Github: mackkcooper
*/

import java.util.Collections;
import java.util.Stack;
import java.util.Vector;

final class GrahamScan {

    static Graph solve(Graph toSolve) {
        if(toSolve.vertices.size() < 3) //no solution
            return null;
        Graph graph = new Graph(toSolve); //copy graph to return as solution
        Vector<Vertex> vertices = copyVector(graph.vertices);
        Vertex start = getStart(vertices); //get first hull point
        sortByPolarAngle(start,vertices); //sorting points in relation to start by polar angle
        Stack<Vertex> stack = new Stack<>(); //init stack
        stack.push(start); //push first 3 vertices onto stack
        stack.push(vertices.get(1));
        stack.push(vertices.get(2));
        Vertex a;
        Vertex b;
        Vertex c;
        for(int i = 3; i < vertices.size(); ++i) {
            a = stack.peek(); //top of stack
            b = stack.get(stack.size()-2); //second to top of stack
            c = vertices.get(i); //ith vertex in vertices

            while(turn(a,b,c) > 0) { //left turn
                stack.pop();
                a = stack.peek();
                b = stack.get(stack.size()-2);
                c = vertices.get(i);
            }
            stack.push(vertices.get(i));
        }
        graph.hullVertices = new Vector<>(100);
        for (Vertex v : stack) {
            graph.addHullVertex(v);
        }
        graph.edges = hullVerticesToEdges(graph.hullVertices);
        return graph;
    }

    private static Vertex getStart(Vector<Vertex> vector) {
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

    private static double getPolarAngle(Vertex a, Vertex b) {
        return Math.atan2(b.y-a.y,b.x-a.x);
    }

    private static void sortByPolarAngle(Vertex origin, Vector<Vertex> vector) {
        for (Vertex v : vector) {
            v.polarAngle = getPolarAngle(origin,v);
        }
        Collections.sort(vector);
        removeCollinear(origin,vector);
    }

    private static void removeCollinear(Vertex origin, Vector<Vertex> vector) {
        int count = 0;
        int i = 1;
        while(i < vector.size()) {
            Vertex a = vector.get(i-1);
            Vertex b = vector.get(i);
            if(a.polarAngle == b.polarAngle) {
                double distanceA = distance(origin, a);
                double distanceB = distance(origin, b);
                if(distanceA > distanceB)
                    vector.remove(i);
                else
                    vector.remove(i-1);
                ++count;
            } else {
                ++i;
            }
        }
        System.out.println("Collinear Removed: " + count);
    }

    private static double distance(Vertex a, Vertex b) {
        return Math.sqrt( ((a.x - b.x) * (a.x - b.x)) + ((a.y - b.y) * (a.y - b.y)) );
    }

    private static int turn(Vertex a, Vertex b, Vertex c) {
        long cross = crossProduct(a,b,c);
        if(cross > 0)
            return 1; //left turn or counter clockwise
        if(cross < 0)
            return -1; //right turn or clockwise
        return 0; //straight or collinear
    }

    private static long crossProduct(Vertex a, Vertex b, Vertex c) {
        return ((b.x - a.x) * (c.y - a.y)) - ((b.y - a.y) * (c.x - a.x));
    }

    private static Vector<Vertex> copyVector(Vector<Vertex> toCopy) {
        Vector<Vertex> temp = new Vector<>();
        for (Vertex v : toCopy) {
            temp.add(new Vertex(v));
        }
        return temp;
    }

    private static Vector<Edge> hullVerticesToEdges(Vector<Vertex> hullVertices) {
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
}
