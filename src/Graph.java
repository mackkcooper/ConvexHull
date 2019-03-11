/*
Author: MacKenzie K. Cooper
Github: mackkcooper
*/

import java.util.Random;
import java.util.Vector;

class Graph {
    Vector <Vertex> vertices = null;
    Vector <Edge> edges = null;
    int vertexCount = 0;
    int edgeCount = 0;
    int vertexRange = 0;

    Graph(int size, int range) {
        generate(size,range);
    }

    Graph(Graph toCopy) {
        vertices = new Vector<>(100);
        edges = new Vector<>(100);
        vertexCount = 0;
        edgeCount = 0;
        vertexRange = toCopy.vertexRange;
        for (Vertex v : toCopy.vertices)
            addVertex(v);
        for (Edge e : toCopy.edges)
            addEdge(e);
    }

    void addVertex(Vertex v) {
        vertices.add(new Vertex(v));
        ++vertexCount;
    }

    void addEdge(Edge e) {
        edges.add(e);
        ++edgeCount;
    }

    void generate(int size, int range) {
        vertices = new Vector<>(100);
        edges = new Vector<>(100);
        vertexCount = 0;
        edgeCount = 0;
        vertexRange = range;
        Random random = new Random();
        for (int i = 0; i < size; ++i)
            addVertex(new Vertex(random.nextInt(vertexRange),random.nextInt(vertexRange)));
    }

    void display() {
        System.out.println(vertexCount + " Vertices:");
        for (Vertex v : vertices) {
            v.display();
            System.out.print(" ");
        }
        System.out.println("\n" + edgeCount + " Edges:");
        for (Edge e : edges) {
            e.display();
            System.out.print(" ");
        }
        System.out.println("\n" + "Vertex Range: " + vertexRange);
    }
}
