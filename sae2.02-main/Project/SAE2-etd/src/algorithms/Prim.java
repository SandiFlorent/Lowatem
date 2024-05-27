/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorithms;

import graphs.Edge;
import graphs.Node;
import graphs.Graph;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

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
    
    private HashMap<Edge, Node> chooseEdge(Graph g,HashMap<Edge,Double> f,Set<Node> s){
        Node u = null;
        Edge chosenEdge = null;
        double costmin=1000000000;
        for (Node v : s){
            //On doit récupérer les getEdges de v. Il faudrait faire un truc du style( g.getEdge(v))
            for(Edge e : g.getEdges()){
                if(s.contains(e.getSource()) && !s.contains(e.getTarget())){
                    if(f.get(e)<costmin){
                        costmin=f.get(e);
                        u=e.getTarget();
                        chosenEdge=e;
                    }
                }
            }
        }
        HashMap<Edge, Node> chosen = new HashMap<>();
        if (chosenEdge != null && u != null) {
            chosen.put(chosenEdge, u);
        }

        return chosen;
        }
        
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