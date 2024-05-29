/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorithms;

import graphs.Graph;
import graphs.Node;
import graphs.Edge;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 *
 * @author ludevaux
 */
public class Dijkstra {
    Graph graph = new Graph();
    
    public Dijkstra(Graph graph) {
        this.graph = graph;
    }
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
            ArrayList<Node> neighbors = graph.getNeighbors(current);
            Node neighbor=neighbors.get(0);
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
    
    
}
