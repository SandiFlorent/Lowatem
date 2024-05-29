/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorithms;

import graphs.Edge;
import graphs.Node;
import graphs.Graph;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;

/**
 *
 * @author mflorent
 */
public class Prim {

    Graph tree = new Graph();

    public Graph prim(Graph g) {
        int numberOfNodes = g.getNodes().size();
        if (numberOfNodes == 0) return tree;
        
        Node InitialNode = g.getNodes().get(new Random().nextInt(numberOfNodes));
        HashSet<Node> knownNodes = new HashSet<>();
        knownNodes.add(InitialNode);
        
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(Edge::getWeight));
        priorityQueue.addAll(g.getInOutEdges(InitialNode));
        
        while (knownNodes.size() < numberOfNodes) {
            Edge minEdge = priorityQueue.poll();
            if (minEdge == null) {
                break;
            }
            
            Node newNode = null;
            if (knownNodes.contains(minEdge.getSource()) && !knownNodes.contains(minEdge.getTarget())) {
                newNode = minEdge.getTarget();
            } else if (knownNodes.contains(minEdge.getTarget()) && !knownNodes.contains(minEdge.getSource())) {
                newNode = minEdge.getSource();
            }
            
            if (newNode != null) {
                knownNodes.add(newNode);
                tree.addNode(minEdge.getSource());
                tree.addNode(minEdge.getTarget());
                tree.addEdge(minEdge);
                priorityQueue.addAll(g.getInOutEdges(newNode));
            }
        }
        
        return tree;
    }

    /**
     * Cette fonction permet de choisir l'arrête de moindre coût dans
     * l'algorithme de Prim
     *
     * @param g le graphe dans lequel on effectue l'algorithme
     * @param NoeudConnus les noeuds déjà visités
     * @return retourne une map contenant l'arrête au moindre coût ainsi que le
     * nouveau atteint
     */
    private HashMap<Edge, Node> ChoisirArrete(Graph g, HashSet<Node> NoeudConnus) {
        Node u = null;
        Edge chosenEdge = null;
        double cost;
        double costmin = Double.MAX_VALUE;
        for (Node v : NoeudConnus) {
            //Pour chaque arrête du graphe g
            for (Edge e : g.getInOutEdges(v)) {
                //On vérifie pour chaque arrête e si le node v est inclu dans e
                // sinon ça ne sert à rien de faire les calculs.
                if (e.contient(v)) {
                    if ((NoeudConnus.contains(e.getSource()) && !NoeudConnus.contains(e.getTarget()))
                            || (!NoeudConnus.contains(e.getSource()) && NoeudConnus.contains(e.getTarget()))) {
                        cost = e.getWeight();
                        if (cost < costmin) {
                            costmin = cost;
                            if (NoeudConnus.contains(e.getTarget())) {
                                u = e.getSource();
                            } else {
                                u = e.getTarget();
                            }
                            chosenEdge = e;
                        }
                    }
                }
            }
        }
        HashMap<Edge, Node> chosen = new HashMap<>();
        if (chosenEdge != null && u != null) {
            chosen.put(chosenEdge, u);
        } else {
            return null;
        }
        return chosen;
    }
}
