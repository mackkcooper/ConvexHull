/*
Author: MacKenzie K. Cooper
Github: mackkcooper
*/

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

class Graph {
    Vector<Vertex> vertices = null;
    Vector<Vertex> hullVertices = null;
    Vector<Edge> edges = null;


    Graph() {}

    Graph(int size, int range) {
        generate(size,range);
    }

    Graph(Graph toCopy) {
        if(toCopy.vertices != null) {
            vertices = new Vector<>(100);
            for (Vertex v : toCopy.vertices)
                addVertex(v);
        }
        if(toCopy.hullVertices != null) {
            hullVertices = new Vector<>(100);
            for (Vertex v : toCopy.hullVertices)
                addHullVertex(v);
        }
        if(toCopy.edges != null) {
            hullVertices = new Vector<>(100);
            for (Edge e : toCopy.edges)
                addEdge(e);
        }
    }

    void addVertex(Vertex v) {
        vertices.add(new Vertex(v));
    }

    void addHullVertex(Vertex v) {
        hullVertices.add(new Vertex(v));
    }

    void addEdge(Edge e) {
        edges.add(new Edge(e));
    }

    private void generate(int size, int range) {
        vertices = new Vector<>(100);
        Random random = new Random();
        for (int i = 0; i < size; ++i)
            addVertex(new Vertex(random.nextInt(range),random.nextInt(range)));
    }

    void display() {
        if(vertices == null) return;
        System.out.println("\n" + vertices.size() + " Vertices:");
        for (Vertex v : vertices) {
            v.display();
            System.out.print(",");
        }
        if(hullVertices == null) return;
        System.out.println("\n" + hullVertices.size() + " Hull Vertices:");
        for (Vertex v : hullVertices) {
            v.display();
            System.out.print(",");
        }
        if(edges == null) return;
        System.out.println("\n" + edges.size() + " Edges:");
        for (Edge e : edges) {
            e.display();
            System.out.print(",");
        }
    }

    void save(String verticesFileName, String edgesFileName) {
        FileWriter out;
        try {
            if(vertices != null) {
                out = new FileWriter(verticesFileName,false);
                for (Vertex v : vertices)
                    out.write(v.x + "," + v.y + "\n");
                out.close();
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        try {
            if(edges != null) {
                out = new FileWriter(edgesFileName,false);
                for (Edge e : edges)
                    out.write(e.a.x + "," + e.a.y + "," + e.b.x + "," + e.b.y + "\n");
                out.close();
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    boolean verify() {
        if(hullVertices == null || hullVertices.size() < 3) return false;
        if(edges == null || edges.size() < 3) return false;
        //verify closed polygon
        int size = edges.size();
        if(!edges.get(0).a.equals(edges.get(size-1).b)) return false;
        for(int i = 1; i < size; ++i) {
            if(!edges.get(i).a.equals(edges.get(i-1).b)) return false;
        }
        //verify
        return true;
    }
}
