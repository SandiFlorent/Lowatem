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
    /**
     *We associated a number to an edge which indexes the nodes associated to it
     */
    private Node source;
    private Node target;
    public HashMap<Node, Node> Edges = new HashMap<>();
    
    public Edge (Node src, Node tgt){
        Edges.put(src, tgt);
        source=src;
        target=tgt;
    }
    public Node getSource(){
        return source;
    }
    public Node getTarget(){
        return target;
    }
    
    
    public Edge (Node src, Node tgt){
        Edges.put(src, tgt);
    }
}
