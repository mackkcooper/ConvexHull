/*
Author: MacKenzie K. Cooper
Github: mackkcooper
*/

class Vertex implements Comparable<Vertex> {
    long x = 0;
    long y = 0;
    double polarAngle = 0;

    Vertex(long x, long y) {
        this.x = x;
        this.y = y;
    }

    Vertex(Vertex toCopy) {
        x = toCopy.x;
        y = toCopy.y;
        polarAngle = toCopy.polarAngle;
    }

    @Override
    public int compareTo(Vertex o) {
        return Double.compare(polarAngle,o.polarAngle);
    }

    void display() {
        System.out.print("(" + x + "," + y + ")");
    }

    boolean equals(Vertex v) {
        return (x == v.x && y == v.y);
    }
}
