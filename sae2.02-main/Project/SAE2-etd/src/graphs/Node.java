/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

/**
 *
 * @author ludevaux
 */
public class Node {

    /**
     * The coordinates of a Node
     */
    public Coord coordinates;
    
    /**
     * The set of a node's neighbours
     */
    private HashSet<Node> neighbours = new HashSet<>();

    /**
     * The constructor of a node that defines it at a certain coordinate
     *
     * @param coordinates the coordinates in which the node will be instanciate
     * with
     */
    public Node(Coord coordinates) {
        this.coordinates = coordinates;
        this.neighbours = new HashSet<>();
    }

    /**
     * A constructor that'll instanciate a node with default coordinates
     */
    public Node() {
        coordinates = (new Coord());
    }

    /**
     * This function will clear the list of neighbours of the node
     */
    public void reinitializeNeighbours() {
        this.neighbours.clear();
    }

    /**
     * Add a node to the list of neighbours
     * @param node the node added to the list of neighbours
     */
    public void addNeighBours(Node node) {
        this.neighbours.add(node);
    }

    /**
     * This function will give access to the list of neighbours of the node
     * @return an HashSet of node containing all the neighbours of the node
     */
    public HashSet<Node> getNeighbours() {
        return this.neighbours;
    }

    /**
     * This function remove a node from the list of neighbours
     * @param node the one to be removed from the list
     */
    public void removeNeighbours(Node node) {
        this.neighbours.remove(node);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        Node tmp = (Node) o;
        return Objects.equals(coordinates, tmp.coordinates);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.coordinates);
        return hash;
    }
}
