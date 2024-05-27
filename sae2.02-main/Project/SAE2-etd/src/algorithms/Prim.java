/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorithms;

import graphs.Coord;
import graphs.Edge;
import graphs.Node;
import graphs.Graph;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author mflorent
 */
public class Prim {

    Graph g;
    Graph tree;
    HashSet<Edge> ACM = new HashSet<>();

    public Prim(Graph g) {
        this.g = g;
    }

    void prim() {
        int numberOfNodes = g.getNodes().size();
        HashSet<Edge> ACM = new HashSet<>();
        Node u = g.getNodes().get(new Random().nextInt(numberOfNodes));
        for (int i=0; i<numberOfNodes; i++){
            
        }
    }
    
    private HashMap<Edge, Node> chooseEdge(Graph g){
        return null;
        //A continuer !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }
    
    /**
     * Renvoie la distance entre 2 points d'une arrête
     * @param edge l'arrête pour laquelle on va calculer la distance des deux points
     * @return la distance
     */
    private double f(Edge edge){
        return edge.getSource().Coordonnées.dist(edge.getTarget().Coordonnées);
    }
}
