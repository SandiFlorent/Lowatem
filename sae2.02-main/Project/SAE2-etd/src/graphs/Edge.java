/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

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

    public Edge() {

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.source);
        hash = 47 * hash + Objects.hashCode(this.target);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Edge other = (Edge) obj;
        if (!Objects.equals(this.source, other.source)) {
            return false;
        }
        return Objects.equals(this.target, other.target);
    }

    /**
     * The constructor that'll create a new edge from two nodes
     *
     * @param src the node designed as the edge's source
     * @param tgt the node designed as the edge's target
     */
    public Edge(Node src, Node tgt) {
        Nodes.put(src, tgt);
        source = src;
        source.reinitializeNeighbours();
        target = tgt;
        target.reinitializeNeighbours();
        source.addNeighBours(target);
        target.addNeighBours(source);
        weight = source.coordinates.dist(tgt.coordinates);
    }

    /**
     * This function give us access to the edge's source
     *
     * @return the edge's source node
     */
    public Node getSource() {
        return source;
    }

    /**
     * This function give us access to the edge's target
     *
     * @return the edge's target node
     */
    public Node getTarget() {
        return target;
    }

    /**
     * This function give us access to the edge's positions
     *
     * @return a list of coordinates of all the intermediate points between the
     * source and target nodes of the edge
     */
    public ArrayList<Coord> getEdgePositions() {
        return positions;
    }

    /**
     * This function give us access to the edge's weight
     *
     * @return a double describing the weight
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * This function will change the coordinates of the positions(the
     * intermediate coordinates known as bends) of an edge
     *
     * @param coord the ArrayList containing the new coordinates for the edge's
     * positions
     */
    public void setEdgePositions(ArrayList<Coord> coord) {
        this.positions = null;
        positions = coord;
    }

    /**
     * This function determines if an edge contains a certain node
     *
     * @param node to node to find
     * @return true if the edge contains the searched node, false otherwise
     */
    public boolean containsTheNode(Node node) {
        return this.Nodes.containsKey(node) || this.Nodes.containsValue(node);
    }

    /**
     * For a given node in an edge, this function gives the target if the given
     * node a source, and a target otherwise
     *
     * @param node the node used in the function
     * @return the target or the source of an edge
     */
    public Node getNeighbour(Node node) {
        if (source.equals(node)) {
            return target;
        } else if (target.equals(node)) {
            return source;
        }
        return null;
    }
}
