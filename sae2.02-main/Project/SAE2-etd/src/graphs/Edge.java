/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphs;

import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 * @author ludevaux
 */
public class Edge {
    
    private Node source;
    private Node target;
    private Double weight;
    public HashMap<Node, Node> Nodes = new HashMap<>();
    private ArrayList<Coord> positions = new ArrayList<>();
    
    public Edge(){
        
    }
    
    public Edge (Node src, Node tgt){
        Nodes.put(src, tgt);
        source= src;
        target = tgt;
        weight = source.Coordonnées.dist(tgt.Coordonnées);
    }
    public Node getSource(){
        return source;
    }
    public Node getTarget(){
        return target;
    }
    
    public ArrayList<Coord> getEdgePositions(){
        return positions;
    }
    
    public Double getWeight(){
        return weight;
    }
    
    public void setEdgePositions(ArrayList<Coord> coord){
        this.positions = null;
        positions = coord;
    }
    /**
     * Cette fonction permet de déterminer si une arrête continent un noeud précis
     * @param noeud le noeud à chercher
     * @return true si l'arrête contient le noeud cherché, false sinon 
     */
    public boolean contient(Node noeud){
        return this.Nodes.containsKey(noeud) || this.Nodes.containsValue(noeud);
    }
}