package org.example;
import java.util.*;

    public class DijkstraAlgo {
    private static final int INF = Integer.MAX_VALUE;

    public static int[] dijkstra(Graph graph, int source) {
        int numVertices = graph.numVertices;
        double[] distances = new double[numVertices];
        int[] previous = new int[numVertices];
        boolean[] visited = new boolean[numVertices];
        PriorityQueue<Pair> queue = new PriorityQueue<>(Comparator.comparingDouble(pair -> pair.weight));

        Arrays.fill(distances, INF);
        Arrays.fill(previous, -1);

        distances[source] = 0;
        queue.add(new Pair(source, 0));

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            int currentNode = current.node;

            if (visited[currentNode]) {
                continue;
            }

            visited[currentNode] = true;

            for (Graph.Edge neighbor : graph.adjacencyList.get(currentNode)) {
                double newDist = distances[currentNode] + neighbor.weight;
                if (newDist < distances[neighbor.vertex]) {
                    distances[neighbor.vertex] = newDist;
                    previous[neighbor.vertex] = currentNode;
                    queue.add(new Pair(neighbor.vertex, newDist));
                }
            }
        }

        return previous;  // We are returning the previous array to reconstruct the path.
    }

    public static List<Integer> getShortestPathTo(int target, int[] previous) {
        List<Integer> path = new ArrayList<>();
        for (int at = target; at != -1; at = previous[at]) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    public static class Pair {
        int node;
        double weight;

        Pair(int node, double weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}
