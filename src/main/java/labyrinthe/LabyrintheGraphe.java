/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labyrinthe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.SingleSourcePaths;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.shortestpath.FloydWarshallShortestPaths;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

/**
 *
 * @author sandi
 */
public class LabyrintheGraphe extends Labyrinthe {

    public Collection<ISalle> Path;
    private DijkstraShortestPath<ISalle, DefaultEdge> dijkstraAlg;
    private SimpleWeightedGraph<ISalle, DefaultEdge> Graph;
    /**
     * The concept of this map is to have the distance between every vertex,
     * It'll avoid having to call again and again and again a method to compute
     * a pathDistance.
     */
    private Map<ISalle, Map<ISalle, Double>> distanceMap;

    public LabyrintheGraphe() {
        super();
        Path = new ArrayList<>();
        creerLabyrinthe();
    }

    /**
     * This method will create the graph version of the labyrinthe and do all
     * the necessary things
     */
    public void creerLabyrinthe() {
        // Initialize the graph
        InitGraph();

        //Then find the shortest path from the entrance to the exit
        Path.addAll(findShortestPath(getEntree(), getSortie()).getVertexList());
    }

    /**
     * This method will initialize the graph from the rooms of the labyrinthe
     */
    private void InitGraph() {
        Graph = new SimpleWeightedGraph<>(DefaultEdge.class);
        dijkstraAlg = new DijkstraShortestPath<>(Graph);
        addAllVertices();
        addAllEdges();
        precalculateDistances();
    }

    /**
     * This method will define all the vertices of the graph
     */
    private void addAllVertices() {
        for (ISalle salle : this) {
            Graph.addVertex(salle);
        }
    }

    /**
     * This method will define all the edges of the graph
     */
    private void addAllEdges() {
        for (ISalle salle : this) {
            for (ISalle accessibleSalle : this) {
                if (!salle.equals(accessibleSalle) && salle.estAdjacente(accessibleSalle)) {
                    Graph.addEdge(salle, accessibleSalle);
                }
            }
        }
    }

    /**
     * This method will define all of the distances between rooms
     */
    private void precalculateDistances() {
        distanceMap = new HashMap<>();

        // Iterate over all vertices
        for (ISalle source : Graph.vertexSet()) {
            distanceMap.put(source, new HashMap<>());

            //Then we use an algorithm to find the shortest distances with a room
            Map<ISalle, Double> distancesFromSource = bfsShortestDistances(source);

            // Store distances for each target
            for (ISalle target : Graph.vertexSet()) {
                if (!source.equals(target)) {
                    
                    //Might not be our case, but if there's no path we define a default value
                    double distance = distancesFromSource.getOrDefault(target, Double.POSITIVE_INFINITY);
                    distanceMap.get(source).put(target, distance);
                } else {
                    distanceMap.get(source).put(target, 0.0);  // Distance to itself is 0
                }
            }
        }
    }

    /**
     * This method will find the shortest distances from a source
     *
     * @param source the room from which we start to search
     * @return the shortest distance of a room
     */
    private Map<ISalle, Double> bfsShortestDistances(ISalle source) {
        Map<ISalle, Double> distances = new HashMap<>();
        Queue<ISalle> queue = new LinkedList<>();
        Set<ISalle> visited = new HashSet<>();

        queue.add(source);
        visited.add(source);
        distances.put(source, 0.0);

        while (!queue.isEmpty()) {
            ISalle current = queue.poll();
            double currentDistance = distances.get(current);

            // Visit all adjacent rooms
            for (DefaultEdge edge : Graph.edgesOf(current)) {
                ISalle neighbor = Graph.getEdgeTarget(edge);
                if (neighbor.equals(current)) {
                    neighbor = Graph.getEdgeSource(edge);
                }

                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    distances.put(neighbor, currentDistance + Graph.getEdgeWeight(edge));
                }
            }
        }
        return distances;
    }

    private GraphPath<ISalle, DefaultEdge> findShortestPath(ISalle startingRoom, ISalle endingRoom) {
        SingleSourcePaths<ISalle, DefaultEdge> source = dijkstraAlg.getPaths(startingRoom);
        return source.getPath(endingRoom);
    }

    @Override
    public Collection<ISalle> chemin(ISalle u, ISalle v) {
        GraphPath<ISalle, DefaultEdge> shortestPath = findShortestPath(u, v);
        if (shortestPath == null) {
            return new ArrayList<>();
        }
        return shortestPath.getVertexList();
    }

    public int distanceGraphe(ISalle s, ISalle t) {

        int distance = distanceMap.get(s).get(t).intValue();
        return distance;
    }

    /**
     * Get the graph
     *
     * @return the graph
     */
    public Graph<ISalle, DefaultEdge> getGraph() {
        return Graph;
    }
}
