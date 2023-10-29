package org.example;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FlightScheduleReader reader = new FlightScheduleReader();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your preferred departure time (HH:mm format): ");
        String preferredTime = scanner.nextLine();

        Graph graph = reader.readFromCSV("flights.csv", preferredTime);
        try (PrintWriter out = new PrintWriter("graph.dot")) {
            out.println(toDOTFormat(graph));
        }

        var yksp = new YensKShortestPaths();

        var runner = new BestFlightsRunner(reader, yksp, graph);

        runner.initiate(preferredTime);
    }
    public static String toDOTFormat(Graph graph) {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph G {\n");

        for (int i = 0; i < graph.numVertices; i++) {
            for (Graph.Edge edge : graph.adjacencyList.get(i)) {
                sb.append(i).append(" -> ").append(edge.vertex).append("[label=\"")
                        .append(edge.weight).append("\"];\n");
            }
        }

        sb.append("}");
        return sb.toString();
    }

}
