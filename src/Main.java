/*
Author: MacKenzie K. Cooper
Github: mackkcooper
*/

import java.io.FileWriter;
import java.io.IOException;

class Main {

    public static void main(String[] args) {
        //runDataGen();
        // /*
        Graph graph = new Graph();
        graph.vertices = ConvexHull.randomControlCase(20,0);
        //graph.vertices = ConvexHull.minHullCase(100);
        graph = runConvexHull(graph,0);
        graph.save("plotter/vertices.csv","plotter/edges.csv");
        // */
    }

    private static void runDataGen() {
        Graph toSolve = new Graph();
        Graph solved;
        FileWriter txtout;
        FileWriter csvout;
        int iterations = 5;
        double totalTimeGraham = 0;
        double totalTimeJarvis = 0;
        int [] sizes = {1000,10000,100000};
        double [] hullPercent = {0,.01,.02,.03,.04};
        System.out.println();
        try {
            txtout = new FileWriter("dataOutput/dataset3.txt",false);
            csvout = new FileWriter("dataOutput/dataset3.csv",false);
            txtout.write("Dataset 3\n");
            for (int n : sizes) {
                txtout.write("\nINPUT SIZE: " + n + "\n");
                txtout.write("#########################\n");
                txtout.write("Minimum Hull Case\n");
                for (int i = 1; i <= iterations; ++i) {
                    txtout.write("Iteration " + i + "\n");
                    toSolve.vertices = ConvexHull.minHullCase(n);

                    System.out.println("Graham / " + n + " / Min");
                    solved = runConvexHull(toSolve,0);
                    txtout.write("Graham: " + solved.solveTime + " , ");
                    totalTimeGraham += solved.solveTime;

                    System.out.println("Jarvis / " + n + " / Min");
                    solved = runConvexHull(toSolve,1);
                    txtout.write("Jarvis: " + solved.solveTime + "\n");
                    totalTimeJarvis += solved.solveTime;
                }
                txtout.write("Avg Graham: " + totalTimeGraham/iterations + " , Avg Jarvis: " + totalTimeJarvis/iterations + "\n");
                txtout.write("#########################\n");
                csvout.write("Graham , " + n + " , Min , " + totalTimeGraham/iterations + "\n");
                csvout.write("Jarvis , " + n + " , Min , " + totalTimeJarvis/iterations + "\n");
                totalTimeGraham = 0;
                totalTimeJarvis = 0;
                for (double per : hullPercent) {
                    txtout.write(per + " Hull Case\n");
                    for (int i = 1; i <= iterations; ++i) {
                        txtout.write("Iteration " + i + "\n");
                        toSolve.vertices = ConvexHull.randomControlCase(n,per);

                        System.out.println("Graham / " + n + " / " + per);
                        solved = runConvexHull(toSolve,0);
                        txtout.write("Graham: " + solved.solveTime + " , ");
                        totalTimeGraham += solved.solveTime;

                        System.out.println("Jarvis / " + n + " / " + per);
                        solved = runConvexHull(toSolve,1);
                        txtout.write("Jarvis: " + solved.solveTime + "\n");
                        totalTimeJarvis += solved.solveTime;
                    }
                    txtout.write("Avg Graham: " + totalTimeGraham/iterations + " , Avg Jarvis: " + totalTimeJarvis/iterations + "\n");
                    txtout.write("#########################\n");
                    csvout.write("Graham , " + n + " , " + per + " , " + totalTimeGraham/iterations + "\n");
                    csvout.write("Jarvis , " + n + " , " + per + " , " + totalTimeJarvis/iterations + "\n");
                    totalTimeGraham = 0;
                    totalTimeJarvis = 0;
                }
            }
            txtout.close();
            csvout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Graph runConvexHull(Graph toSolve, int alg) {
        if(toSolve == null) return null;
        Graph solved;
        switch(alg) {
            case 0:
                solved = GrahamScan.solve(toSolve);
                break;
            case 1:
                solved = JarvisMarch.solve(toSolve);
                break;
            default:
                System.out.println("No algorithm selected.");
                return null;
        }
        if(solved == null) {
            System.out.println("Failure to solve.");
            return null;
        }
        System.out.println("Time: " + solved.solveTime + " ms");
        solved.displayShape();
        System.out.println();
        //System.out.println("Solved: " + ConvexHull.verify(solved)");
        return solved;
    }

    private static void runConvexHull(int alg, String fileName) {
        Graph toSolve;
        if(fileName == null)
            toSolve = new Graph(500,10000);
        else
            toSolve = new Graph(fileName);
        runConvexHull(toSolve,alg);
    }

}
