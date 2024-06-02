/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorithms;

import graphs.Coord;
import graphs.Graph;
import graphs.Node;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author sandi
 */
public class BFS {

    public ArrayList<Coord> bfsShortestPath(Graph g, Node start, Node end) {
        if (start.equals(end)) {
            ArrayList<Coord> singleNodePath = new ArrayList<>();
            singleNodePath.add(start.Coordonnées);
            return singleNodePath;
        }

        Map<Node, Node> cameFrom = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            for (Node neighbor : g.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    cameFrom.put(neighbor, current);
                    queue.add(neighbor);
                    if (neighbor.equals(end)) {
                        return reconstructPath(cameFrom, start, end);
                    }
                }
            }
        }

        return new ArrayList<>(); // Return empty list if no path found
    }

    private static ArrayList<Coord> reconstructPath(Map<Node, Node> cameFrom, Node start, Node end) {
        ArrayList<Coord> path = new ArrayList<>();
        for (Node at = end; at != null; at = cameFrom.get(at)) {
            if (at != start && at != end) {
                path.add(at.Coordonnées);
            }
        }
        Collections.reverse(path);
        return path;
    }
}
