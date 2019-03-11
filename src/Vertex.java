/*
Author: MacKenzie K. Cooper
Github: mackkcooper
*/

class Vertex {
    int x;
    int y;

    Vertex(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Vertex(Vertex toCopy) {
        x = toCopy.x;
        y = toCopy.y;
    }

    void display() {
        System.out.print("(" + x + "," + y + ")");
    }
}
