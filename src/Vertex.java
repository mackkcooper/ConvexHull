/*
Author: MacKenzie K. Cooper
Github: mackkcooper
*/

import java.util.Vector;

class Vertex implements Comparable<Vertex> {
    double x;
    double y;
    double sortParam;

    Vertex(double x, double y) {
        this.x = x;
        this.y = y;
        sortParam = 0;
    }

    Vertex(Vertex toCopy) {
        x = toCopy.x;
        y = toCopy.y;
        sortParam = toCopy.sortParam;
    }

    @Override
    public int compareTo(Vertex o) {
        return Double.compare(sortParam,o.sortParam);
    }

    void display() {
        System.out.print("(" + x + "," + y + ")");
    }

    boolean equals(Vertex v) {
        return (x == v.x && y == v.y);
    }

    static Vector<Vertex> copyVector(Vector<Vertex> toCopy) {
        Vector<Vertex> temp = new Vector<>();
        for (Vertex v : toCopy) {
            temp.add(new Vertex(v));
        }
        return temp;
    }
}
