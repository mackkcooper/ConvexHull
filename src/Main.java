/*
Author: MacKenzie K. Cooper
Github: mackkcooper
*/


class Main {

    public static void main(String[] args) {
        Graph graph = new Graph(100,10000);
        Graph solved = GrahamScan.solve(graph);
        if(solved != null) {
            System.out.println("Solved: " + solved.verify());
            //solved.display();
            solved.save("plotter/vertices.csv", "plotter/edges.csv");
        }
    }
}
