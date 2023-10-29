package org.example;
import java.util.*;

public class YensKShortestPaths {
    public List<List<Integer>> yenKSP(Graph graph, int source, int target, int K) {
        List<List<Integer>> A = new ArrayList<>();
        PriorityQueue<CandidatePath> B = new PriorityQueue<>(Comparator.comparingInt(CandidatePath::getCost));

        List<Integer> firstPath = DijkstraAlgo.getShortestPathTo(target, DijkstraAlgo.dijkstra(graph, source));
        A.add(firstPath);

        for (int k = 1; k < K; k++) {
            List<Integer> prevPath = A.get(k - 1);

            for (int i = 0; i < prevPath.size() - 1; i++) {
                int spurNode = prevPath.get(i);
                List<Integer> rootPath = prevPath.subList(0, i + 1);

                for (List<Integer> path : A) {
                    if (path.size() > i && rootPath.equals(path.subList(0, i + 1))) {
                        graph.removeEdge(path.get(i), path.get(i + 1));
                    }
                }

                int[] spurPrevious = DijkstraAlgo.dijkstra(graph, spurNode);
                List<Integer> spurPath = DijkstraAlgo.getShortestPathTo(target, spurPrevious);

                if (!spurPath.isEmpty() && spurPath.get(0) == spurNode) {
                    List<Integer> totalPath = new ArrayList<>(rootPath);
                    totalPath.addAll(spurPath.subList(1, spurPath.size()));
                    B.add(new CandidatePath(totalPath));
                }

                // Restore edges to graph
                graph.restoreEdges();
            }

            if (B.isEmpty()) {
                break;
            }

            A.add(B.poll().getPath());
        }

        return A;
    }

    public List<List<Integer>> postHocEvaluation(List<List<Integer>> paths) {
        // For this example, let's sort the paths by their length for simplicity
        paths.sort(Comparator.comparingInt(List::size));
        return paths;
    }

    public static class CandidatePath {
        private final List<Integer> path;
        private final int cost;  // For simplicity, let's assume cost is the path length.

        public CandidatePath(List<Integer> path) {
            this.path = new ArrayList<>(path);
            this.cost = path.size();
        }

        public List<Integer> getPath() {
            return path;
        }

        public int getCost() {
            return cost;
        }
    }
}

