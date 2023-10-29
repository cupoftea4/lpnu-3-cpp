package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FlightScheduleReader {

    private final Map<String, Integer> cityToIndex = new HashMap<>();
    private int indexCounter = 0;
    private final Map<Integer, String> indexToCity = new HashMap<>();

    public Graph readFromCSV(String filename, String desiredTime) {
        Graph graph = new Graph(100, desiredTime); // starting with an arbitrary size

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                int from = getCityIndex(parts[0]);
                int to = getCityIndex(parts[1]);
                String departureTime = parts[2];
                String arrivalTime = parts[3];
                double distance = Double.parseDouble(parts[4]);
                double cost = Double.parseDouble(parts[5]);

                Graph.Edge edge = new Graph.Edge(to, departureTime, arrivalTime, distance, cost, desiredTime);
                graph.addEdge(from, to, edge);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }

    private int getCityIndex(String city) {
        if (!cityToIndex.containsKey(city)) {
            cityToIndex.put(city, indexCounter);
            indexToCity.put(indexCounter, city);
            indexCounter++;
        }
        return cityToIndex.get(city);
    }



    public String getCityFromIndex(int index) {
        return indexToCity.get(index);
    }

}
