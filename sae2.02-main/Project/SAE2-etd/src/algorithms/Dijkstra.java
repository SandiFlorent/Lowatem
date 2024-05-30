/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorithms;

import graphs.Graph;
import graphs.Node;
import graphs.Edge;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 *
 * @author ludevaux
 */
public class Dijkstra {
    Graph graph = new Graph();
    
    /*
    Initialisation de l'algorithm de Dijkstra
    */
    private void Initialisation (HashMap<Node, Double> distances, Node start) {
        for (Node node : graph.GetAllNodes()) {
            // On attribue à tous les noeuds du graph, une valeur infini
            distances.put(node, Double.MAX_VALUE); 
        }
        // On attribue à la valeur de départ, la valeur 0
        distances.put(start, 0.0); // d[sdeb] := 0
    }
    
    private void processNeighbors(PriorityQueue<Node> queue, HashMap<Node, Double> distances, HashMap<Node, Node> predecessors, HashSet<Node> visited, Node current) {
        for (Edge edge : graph.getInOutEdges(current)) {
            Node neighbor = edge.getNeighbor(current);
            if (visited.contains(neighbor)){
                continue;
            }
            double newDist = distances.get(current) + current.Coordonnées.dist(neighbor.Coordonnées);
            if (newDist < distances.getOrDefault(neighbor, Double.MAX_VALUE)) {
                distances.put(neighbor, newDist);
                predecessors.put(neighbor, current);
                queue.add(neighbor);
            }
        }
    }
    private ArrayList<Node> reconstructPath(HashMap<Node, Node> predecessors, Node goal) {
        ArrayList<Node> path = new ArrayList<>();
        for (Node at = goal; at != null; at = predecessors.get(at)) {
            path.add(0, at);
        }
        return path;
    }
    public ArrayList<Node> findShortestPath(Node start, Node goal) {
        HashMap<Node, Double> distances = new HashMap<>();
        HashMap<Node, Node> predecessors = new HashMap<>();
        HashSet<Node> visited = new HashSet<>();

        // Création du comparateur
        Comparator<Node> comparator = Comparator.comparingDouble(node -> distances.get(node));

        // Initialisation de la file de priorité avec le comparateur
        PriorityQueue<Node> queue = new PriorityQueue<>(comparator);

        Initialisation(distances, start);
        queue.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (!visited.add(current)) continue; 

            if (current.equals(goal)) {
                return reconstructPath(predecessors, goal);
            }

            processNeighbors(queue, distances, predecessors, visited, current);
        }
        return null; // Si aucun chemin trouvé
    }
    
}
