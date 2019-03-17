/*
Author: MacKenzie K. Cooper
Github: mackkcooper
*/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

class Graph {
    Vector<Vertex> vertices = null;
    Vector<Vertex> hullVertices = null;
    Vector<Edge> edges = null;
    long solveTime = -1; //in milliseconds

    Graph() {}

    Graph(int size, int range) {
        generate(size,range);
    }

    Graph(String fileName) {
        load(fileName);
    }

    Graph(Graph toCopy) {
        if(toCopy.vertices != null) {
            vertices = new Vector<>();
            for (Vertex v : toCopy.vertices)
                addVertex(v);
        }
        if(toCopy.hullVertices != null) {
            hullVertices = new Vector<>();
            for (Vertex v : toCopy.hullVertices)
                addHullVertex(v);
        }
        if(toCopy.edges != null) {
            edges = new Vector<>();
            for (Edge e : toCopy.edges)
                addEdge(e);
        }
        solveTime = 0;
    }

    private void addVertex(Vertex v) {
        vertices.add(new Vertex(v));
    }

    void addHullVertex(Vertex v) {
        hullVertices.add(new Vertex(v));
    }

    private void addEdge(Edge e) {
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

    void displayShape() {
        if(vertices != null)
            System.out.print(vertices.size());
        else
            System.out.print(0);
        System.out.print(" v, ");
        if(hullVertices != null)
            System.out.print(hullVertices.size());
        else
            System.out.print(0);
        System.out.print(" hv, ");
        if(edges != null)
            System.out.print(edges.size());
        else
            System.out.print(0);
        System.out.print(" e\n");
    }

    void save(String verticesFileName, String edgesFileName) {
        FileWriter out;
        int size;
        try {
            if(vertices != null) {
                out = new FileWriter(verticesFileName,false);
                size = vertices.size();
                for (Vertex v : vertices) {
                    out.write(v.x + "," + v.y);
                    if (v != vertices.get(size-1))
                        out.write("\n");
                }
                out.close();
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        try {
            if(edges != null) {
                out = new FileWriter(edgesFileName,false);
                size = edges.size();
                for (Edge e : edges) {
                    out.write(e.a.x + "," + e.a.y + "," + e.b.x + "," + e.b.y);
                    if(e != edges.get(size-1))
                        out.write("\n");
                }
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load(String fileName) {
        Vector<Vertex> input;
        String [] line;
        try {
            Scanner scanner = new Scanner(new File(fileName));
            input = new Vector<>(100);
            int a;
            int b;
            while (scanner.hasNext()) {
                line = scanner.nextLine().split(",");
                if(line.length > 1) {
                    a = Integer.parseInt(line[0]);
                    b = Integer.parseInt(line[1]);
                    input.add(new Vertex(a,b));
                }
            }
            scanner.close();
            vertices = input;
            hullVertices = null;
            edges = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
