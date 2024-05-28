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

    Graph tree = new Graph();
    HashSet<Edge> ACM = new HashSet<>();

    public Graph prim(Graph g) {
        Edge a = new Edge();
        int numberOfNodes = g.getNodes().size();
        // Le seule et unique but de cette variable est de récupérer les valeurs
        // fournie par la méthode ChoisirArrete
        HashMap<Edge, Node> arreteChoisie = new HashMap<>();
        //Permet de prendre au hasard un noeud dans le graphe
        Node u = g.getNodes().get(new Random().nextInt(numberOfNodes));
        HashSet<Node> s = new HashSet<>();
        s.add(u);
        for (int i = 0; i < numberOfNodes; i++) {
            arreteChoisie = ChoisirArrete(g, s);
            //Permet de prendre la prochaine clé de la HashMap
            //Sachant qu'il n'y a qu'une seule paire, on aura la bonne
            a = arreteChoisie.keySet().iterator().next();
            ACM.add(a);
            s.add(arreteChoisie.get(a));
        }
        for (Edge e : ACM){
            tree.addNode(e.getSource());
            tree.addNode(e.getTarget());
            tree.addEdge(e);
        }
        return tree;
    }

    /**
     * Cette fonction permet de choisir l'arrête de moindre coût dans l'algorithme
     * de Prim
     * @param g le graphe dans lequel on effectue l'algorithme
     * @param s les noeuds déjà visités
     * @return retourne une map contenant l'arrête au moindre coût ainsi que le nouveau
     * atteint
     */
    private HashMap<Edge, Node> ChoisirArrete(Graph g, HashSet<Node> s) {
        Node u = null;
        Edge chosenEdge = null;
        double cost;
        double costmin = 1000000000;
        for (Node v : s) {
            //Pour chaque arrête du graphe g
            for (Edge e : g.getEdges()) {
                //On vérifie pour chaque arrête e si le node v est inclu dans e
                // sinon ça ne sert à rien de faire les calculs.
                if (e.contient(v)) {
                    if (s.contains(e.getSource()) && !s.contains(e.getTarget())
                            || !s.contains(e.getSource()) && s.contains(e.getTarget())) {
                        cost = f(e);
                        if (cost < costmin) {
                            costmin = cost;
                            u = e.getTarget();
                            chosenEdge = e;
                        }
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

    /**
     * Renvoie la distance entre 2 points d'une arrête
     * @param edge l'arrête pour laquelle on va calculer la distance des deux
     * pointsnumberOfNodes
     * @return la distance
     */
    private double f(Edge edge) {
        return edge.getSource().Coordonnées.dist(edge.getTarget().Coordonnées);
    }
}
