package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class BestFlightsRunner {
    private final FlightScheduleReader reader;
    private final Graph graph;
    private final YensKShortestPaths yksp;

    public BestFlightsRunner(FlightScheduleReader reader, YensKShortestPaths yksp, Graph graph) {
        this.reader = reader;
        this.yksp = yksp;
        this.graph = graph;
    }

    public void initiate(String preferredTime) {
        Scanner scanner = new Scanner(System.in);

        printGraphSchedule();

        System.out.print("How would you like to sort the routes? (cost/distance/time): ");
        String sortBy = scanner.nextLine();

        List<List<Integer>> kShortestPaths = yksp.yenKSP(graph, 0, 3, 3);

        System.out.println("Preferred time: " + preferredTime);
        var sortedRoutes = sortRoutes(kShortestPaths, sortBy, preferredTime);
        printBestRoutes(sortedRoutes, sortBy, 3);
    }

    private Map<Integer, List<Integer>> sortRoutes(List<List<Integer>> routes, String sortBy, String preferredTime) {
        return routes.stream()
                .sorted(Comparator.comparingInt(route -> routeWeight(route, sortBy, preferredTime)))
                .collect(Collectors.toMap(
                        route -> routeWeight(route, sortBy, preferredTime),
                        route -> route,
                        (route1, route2) -> route1,  // In case of key collisions, choose route1
                        LinkedHashMap::new  // To keep the map in insertion order
                ));
    }


    private int routeWeight(List<Integer> route, String sortBy, String preferredDepartureTime) {
        if (Objects.equals(sortBy, "time")) {
            var len = route.size();
            var lastEdge = graph.getEdge(route.get(len - 2), route.get(len - 1));
            return Graph.Edge.timeDifferenceInMinutes(lastEdge.arrivalTime, preferredDepartureTime);
        }

        int totalWeight = 0;

        for (int i = 0; i < route.size() - 1; i++) {
            Graph.Edge edge = graph.getEdge(route.get(i), route.get(i+1));
            totalWeight += computeWeight(edge, sortBy);
        }

        return totalWeight;
    }

    private int computeWeight(Graph.Edge edge, String sortBy) {
        return switch (sortBy) {
            case "cost" -> (int) edge.cost;
            case "distance" -> (int) edge.distance;
            default -> 0; // or throw an exception for unrecognized option
        };
    }



    private void printBestRoutes(Map<Integer, List<Integer>> sortedRoutesWithWeights, String sortBy, int topN) {
        System.out.println("\nTop " + topN + " Routes:");

        var sortingByTime = Objects.equals(sortBy, "time");

        int count = 0;
        for (Map.Entry<Integer, List<Integer>> entry : sortedRoutesWithWeights.entrySet()) {
            if (count >= topN) break;


            List<Integer> route = entry.getValue();
            Integer weight = entry.getKey();
            String weightStr = weight.toString();
            if (sortingByTime) {
                weightStr = convertToHoursAndMinutes(weight);
            }

            List<String> names = route.stream().map(reader::getCityFromIndex).collect(Collectors.toList());
            System.out.println(String.join(" -> ", names) + " | Weight: " + weightStr + (sortingByTime ? " til destination" : ""));

            count++;
        }
    }

    public String convertToHoursAndMinutes(int durationInMinutes) {
        int hours = durationInMinutes / 60;
        int minutes = durationInMinutes % 60;
        return hours + "h " + minutes + "m";
    }

    public void printGraphSchedule() {
        System.out.println("Schedule:");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-15s %-15s %-15s %-15s %-10s %-10s\n", "Departure", "Destination", "Depart Time", "Arrival Time", "Distance", "Cost");

        for (int i = 0; i < graph.numVertices; i++) {
            for (Graph.Edge edge : graph.adjacencyList.get(i)) {
                System.out.printf("%-15s %-15s %-15s %-15s %-10.2f %-10.2f\n",
                        reader.getCityFromIndex(i),
                        reader.getCityFromIndex(edge.vertex),
                        edge.departureTime,
                        edge.arrivalTime,
                        edge.distance,
                        edge.cost);
            }
        }

        System.out.println("--------------------------------------------------------------------------------");
    }


}
