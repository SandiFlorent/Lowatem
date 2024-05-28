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

/**
 *
 * @author mflorent
 */
public class Prim {

    Graph tree = new Graph();

    public Graph prim(Graph g) {
        Edge EdgeChoisi;
        int numberOfNodes = g.getNodes().size();
        // Le seule et unique but de cette variable est de récupérer les valeurs
        // fournie par la méthode ChoisirArrete
        HashMap<Edge, Node> arreteChoisie;
        //Permet de prendre au hasard un noeud dans le graphe
        Node u = g.getNodes().get(new Random().nextInt(numberOfNodes));
        HashSet<Node> NoeudConnus = new HashSet<>();
        NoeudConnus.add(u);

        for (int i = 0; i < numberOfNodes - 1; i++) {
            arreteChoisie = ChoisirArrete(g, NoeudConnus);
            //Ne fonctionne pas avec les graphes non connexes !!!!!!! Gérer ça avec un set des sommets non explorés.
            if (arreteChoisie == null) {
                continue;
            }
            //Permet de prendre la prochaine clé de la HashMap
            //Sachant qu'il n'y a qu'une seule paire, on aura la bonne
            EdgeChoisi = arreteChoisie.keySet().iterator().next();
            Node noeudChoisi = arreteChoisie.get(EdgeChoisi);
            NoeudConnus.add(noeudChoisi);
            tree.addNode(EdgeChoisi.getSource());
            tree.addNode(EdgeChoisi.getTarget());
            tree.addEdge(EdgeChoisi);
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
                        cost = f(e);
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

    /**
     * Renvoie la distance entre 2 points d'une arrête
     *
     * @param edge l'arrête pour laquelle on va calculer la distance des deux
     * pointsnumberOfNodes
     * @return la distance
     */
    private double f(Edge edge) {
        return edge.getSource().Coordonnées.dist(edge.getTarget().Coordonnées);
    }
}
