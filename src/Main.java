/*
Author: MacKenzie K. Cooper
Github: mackkcooper
*/


class Main {

    public static void main(String[] args) {
        Graph graph = new Graph(20,100);
        graph.display();
        GrahamScan gs = new GrahamScan();
        System.out.println("Solved: " + gs.solve(graph));
    }
}
