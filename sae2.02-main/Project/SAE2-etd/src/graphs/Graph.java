/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphs;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author ludevaux
 */
public class Graph implements IGraph{
    
    ArrayList<Edge> AllEdges = new ArrayList<>(); 
    ArrayList<Node> allNodes = new ArrayList<>();
    
    @Override
    public Node addNode() {
        Node aNode = new Node();
        allNodes.add(aNode);
        return aNode;
    }
    @Override
    public Node addNode(Node n) {
        allNodes.add(n);
        return n;
    }
    @Override
    public Edge addEdge(Edge e) {
        AllEdges.add(e);
        return e;
    }

    @Override
    public Edge addEdge(Node src, Node tgt) {
        Edge tempNode = new Edge(src, tgt);
        AllEdges.add(tempNode);
        return tempNode;
    }

    @Override
    public void delNode(Node n) {
        allNodes.remove(n);
    }

    @Override
    public void delEdge(Edge e) {
        AllEdges.remove(e);
    }

    @Override
    public int numberOfNodes() {
        return allNodes.size();
    }

    @Override
    public int numberOfEdges() {
        return AllEdges.size();
    }

    @Override
    public ArrayList<Node> getNeighbors(Node n) {
        ArrayList<Node> allNeighbors = new ArrayList<>();
        for (Edge edge : AllEdges){
            if(edge.getSource().equals(n))
                allNeighbors.add(edge.getSource());
            else if(edge.getTarget().equals(n))
                allNeighbors.add(edge.getTarget());
        }
        return allNeighbors;
    }

    @Override
    public ArrayList<Node> getSuccesors(Node n) {
        ArrayList<Node> allNeighbors = new ArrayList<>();
        for (Edge edge : AllEdges){
            if(edge.getSource().equals(n))
                allNeighbors.add(edge.getTarget());
        }
        return allNeighbors;
    }

    @Override
    public ArrayList<Node> getPredecessors(Node n) {
        ArrayList<Node> allNeighbors = new ArrayList<>();
        for (Edge edge : AllEdges){
            if(edge.getTarget().equals(n))
                allNeighbors.add(edge.getSource());
        }
        return allNeighbors;
    }
    

    @Override
    public ArrayList<Edge> getInOutEdges(Node n) {
        ArrayList<Edge> inOutEdges = new ArrayList<>();
        inOutEdges.addAll(getInEdges(n));
        inOutEdges.addAll(getOutEdges(n));
        return inOutEdges;
    }

    @Override
    public ArrayList<Edge> getInEdges(Node n) {
        ArrayList<Edge> inEdges = new ArrayList<>();
        for (Edge edge : AllEdges){
            if (edge.getTarget() == n){
                inEdges.add(edge);
            }
        }
        return inEdges;
    }

    @Override
    public ArrayList<Edge> getOutEdges(Node n) {
        ArrayList<Edge> outEdges = new ArrayList<>();
        for (Edge edge : AllEdges){
            if (edge.getSource() == n){
                outEdges.add(edge);
            }
        }
        return outEdges;
    }

    @Override
    public ArrayList<Node> getNodes() {
        return allNodes;
    }

    @Override
    public ArrayList<Edge> getEdges() {
        return AllEdges;
    }

    @Override
    public Node source(Edge e) {
        return e.getSource();
    }

    @Override
    public Node target(Edge e) {
        return e.getTarget();
    }

    @Override
    public int inDegree(Node n) {
        return getInEdges(n).size();
    }

    @Override
    public int outDegree(Node n) {
        return getOutEdges(n).size();
    }

    @Override
    public int degree(Node n) {
        return getInEdges(n).size()+getOutEdges(n).size();
    }

    @Override
    public boolean existEdge(Node src, Node tgt, boolean oriented) {
        if(oriented){
            return(AllEdges.contains(new Edge(src,tgt)));
        }
        else{
            return(AllEdges.contains(new Edge(src,tgt))||(AllEdges.contains(new Edge(tgt,src))));
        }
    }
    @Override
    public Edge getEdge(Node src, Node tgt, boolean oriented) {
        int compteur = 0;
        Edge x = null;
        if(existEdge(src, tgt, oriented)){
            while(x == null){
                if(new Edge(src, tgt).equals(AllEdges.get(compteur))){
                    x = AllEdges.get(compteur);
                }
                compteur++;
            }
        }
        return x;
    }

    @Override
    public Coord getNodePosition(Node n) {
        return n.Coordonnées;
    }

    @Override
    public ArrayList<Coord> getEdgePosition(Edge e) {
        ArrayList<Coord> positions = new ArrayList<>();
        positions.add(e.getSource().Coordonnées);
        positions.add(e.getTarget().Coordonnées);
        return positions;
    }

    @Override
    public void setNodePosition(Node n, Coord c) {
        n.Coordonnées=c;
    }

    @Override
    public void setEdgePosition(Edge e, ArrayList<Coord> bends) {
        e.setEdgePositions(bends);
    }

    @Override
    public void setAllNodesPositions(Coord c) {
        for (Node node : allNodes){
            setNodePosition(node, c);
        }
    }

    @Override
    public void setAllEdgesPositions(ArrayList<Coord> bends) {
    for (Edge edge : AllEdges){
            setEdgePosition(edge,bends);
        }
    }

    @Override
    public ArrayList<Coord> getBoundingBox() {
        double miny=allNodes.get(0).Coordonnées.getY();
        double maxy=allNodes.get(0).Coordonnées.getY();
        double maxx=allNodes.get(0).Coordonnées.getX();
        double minx=allNodes.get(0).Coordonnées.getX();
        ArrayList<Coord> BoxCoord = new ArrayList<>();
        for(Node a : allNodes){
            if(minx>a.Coordonnées.getX()){
                minx=a.Coordonnées.getX();
            }
            if(maxx<a.Coordonnées.getX()){
                maxx=a.Coordonnées.getX();
            }
            if(miny>a.Coordonnées.getY()){
                miny=a.Coordonnées.getY();
            }
            if(maxy<a.Coordonnées.getY()){
                maxy=a.Coordonnées.getY();
            }
            
        }
        Coord pointA = new Coord(minx,miny);
        Coord pointB = new Coord(maxx,maxy);
        BoxCoord.add(pointA);
        BoxCoord.add(pointB);
        return BoxCoord;
    }

    @Override
    public Graph getMinimumSpanningTree() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void bundle() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
