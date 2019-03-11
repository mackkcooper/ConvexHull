/*
Author: MacKenzie K. Cooper
Github: mackkcooper
*/

class Edge {
    Vertex a;
    Vertex b;

    Edge(Vertex a, Vertex b) {
        this.a = a;
        this.b = b;
    }

    Edge(Edge toCopy) {
        a = new Vertex(toCopy.a);
        b = new Vertex(toCopy.b);
    }

    void display(){
        System.out.print("<");
        a.display();
        System.out.print(",");
        b.display();
        System.out.print(">");
    }
}
