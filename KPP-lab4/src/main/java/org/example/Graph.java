package org.example;

import java.util.*;

public class Graph {
    public int numVertices;
    public List<List<Edge>> adjacencyList;
    private final Map<Integer, List<Edge>> removedEdges;

//    private final String desiredTime;

    public Graph(int numVertices, String desiredTime) {
        this.numVertices = numVertices;
        this.adjacencyList = new ArrayList<>();
        this.removedEdges = new HashMap<>();

//        this.desiredTime = desiredTime;

        for (int i = 0; i < numVertices; i++) {
            this.adjacencyList.add(new LinkedList<>());
        }
    }

    // We'll update this method later
    public void addEdge(int from, int to, Edge edge) {
        adjacencyList.get(from).add(edge);
    }

    public void removeEdge(int from, int to) {
        Iterator<Edge> iterator = adjacencyList.get(from).iterator();
        while (iterator.hasNext()) {
            Edge edge = iterator.next();
            if (edge.vertex == to) {
                removedEdges.computeIfAbsent(from, k -> new LinkedList<>()).add(edge);
                iterator.remove();
            }
        }
    }

    public void restoreEdges() {
        for (Map.Entry<Integer, List<Edge>> entry : removedEdges.entrySet()) {
            int from = entry.getKey();
            List<Edge> edges = entry.getValue();
            adjacencyList.get(from).addAll(edges);
        }
        removedEdges.clear();
    }

    public Edge getEdge(int from, int to) {
        List<Edge> edgesFromVertex = adjacencyList.get(from);

        for (Edge edge : edgesFromVertex) {
            if (edge.vertex == to) {
                return edge;
            }
        }

        return null;
    }

    public static class Edge {
        int vertex;
        String departureTime;
        String arrivalTime;
        double distance;
        double cost;
        String desiredTime;
        double weight; // This will be computed based on user preferences

        Edge(int vertex, String departureTime, String arrivalTime, double distance, double cost, String desiredTime) {
            this.vertex = vertex;
            this.departureTime = departureTime;
            this.arrivalTime = arrivalTime;
            this.distance = distance;
            this.cost = cost;
            this.desiredTime = desiredTime;
            this.weight = computeWeight(); // We'll implement this later
        }

        private int computeWeight() {
            int penalty = timeDifferenceInMinutes(departureTime, this.desiredTime);

            // For this example: weight = cost + (distance/100) + (timeDifference/60)
            return (int)(cost + distance/100 + penalty/60);
        }

        public static int timeDifferenceInMinutes(String time1, String time2) {
            String[] parts1 = time1.split(":");
            String[] parts2 = time2.split(":");

            int time1InMins = Integer.parseInt(parts1[0]) * 60 + Integer.parseInt(parts1[1]);
            int time2InMins = Integer.parseInt(parts2[0]) * 60 + Integer.parseInt(parts2[1]);

            return Math.abs(time1InMins - time2InMins);
        }
    }
}
