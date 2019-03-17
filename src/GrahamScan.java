/*
Author: MacKenzie K. Cooper
Github: mackkcooper
*/

import java.util.Collections;
import java.util.Stack;
import java.util.Vector;

abstract class GrahamScan extends ConvexHull {

    static Graph solve(Graph toSolve) {
        long time = System.currentTimeMillis();
        if(notSolvable(toSolve)) return null;
        Graph graph = new Graph(toSolve); //copy graph to return as solution
        Vector<Vertex> vertices = Vertex.copyVector(graph.vertices);
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
        graph.solveTime = System.currentTimeMillis() - time;
        return graph;
    }

    private static double getPolarAngle(Vertex a, Vertex b) {
        return Math.atan2(b.y-a.y,b.x-a.x);
    }

    private static void sortByPolarAngle(Vertex origin, Vector<Vertex> vector) {
        for (Vertex v : vector) {
            v.sortParam = getPolarAngle(origin,v);
        }
        Collections.sort(vector);
    }

}
