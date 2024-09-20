/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labyrinthe;

import java.util.ArrayList;
import java.util.Collection;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.SingleSourcePaths;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import vue2D.javafx.Dessin;

/**
 *
 * @author sandi
 */
public class LabyrintheGraphe extends Labyrinthe {

    public Collection<ISalle> Path;
    private DijkstraShortestPath<ISalle, DefaultEdge> dijkstraAlg;
    private SimpleWeightedGraph<ISalle, DefaultEdge> Graph;

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
        addAllVertices();
        addAllEdges();
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
                if (salle.estAdjacente(accessibleSalle)) {
                    Graph.addEdge(salle, accessibleSalle);
                }
            }
        }
    }
    
    private void setWeight(){
        
    }

    private GraphPath<ISalle, DefaultEdge> findShortestPath(ISalle startingRoom, ISalle endingRoom) {
        dijkstraAlg = new DijkstraShortestPath<>(Graph);
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
    
    public int distanceGraphe(ISalle s, ISalle t){

        // Calcul de la distance entre deux sommets
        double distance = dijkstraAlg.getPathWeight(s, t);
        return (int)distance;
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
