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

    /**
     * This function finds the shortest path between two nodes in a graph if it
     * exists
     *
     * @param g the graph in which we'll search the path
     * @param start the node from which we start
     * @param end the node to reach
     * @return an ArrayList of the coords of each node in the path or returns
     * null if there's no path
     */
    public ArrayList<Coord> bfsShortestPath(Graph g, Node start, Node end) {
        // This function shall be used with the MST, thus there's no questionning about the weight
        if (start.equals(end)) {
            ArrayList<Coord> singleNodePath = new ArrayList<>();
            singleNodePath.add(start.coordinates);
            return singleNodePath;
        }

        // This map will be used to define the nextNode as the successor and the actual node as a predecessor
        Map<Node, Node> cameFrom = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        //It's simple, we take each neighbour until one of the neighbour is the end
        //It's basically made to compute on the MST, so there's only ONE path
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

    /**
     * This function's purpose is to reconstruct the shortest path of a end node
     * to the start node
     *
     * @param cameFrom is the predecessors/successors map that will let us know
     * which node came before another one
     * @param start the starting node in the path
     * @param end the end node to reach at the end of the path
     * @return the shortest path founded
     */
    private static ArrayList<Coord> reconstructPath(Map<Node, Node> cameFrom, Node start, Node end) {
        ArrayList<Coord> path = new ArrayList<>();
        //There's only one predecessor per node, so starting by the end node will inevitably lead us to the start node
        for (Node at = end; at != null; at = cameFrom.get(at)) {
            if (at != start && at != end) {
                path.add(at.coordinates);
            }
        }
        //We started by the end to it's necessary to reverse the collection.
        Collections.reverse(path);
        return path;
    }
}
