/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphs;

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
     * The constructor of a node that defines it at a certain coordinate
     *
     * @param coordinates the coordinates in which the node will be instanciate
     * with
     */
    public Node(Coord coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * A constructor that'll instanciate a node with default coordinates
     */
    public Node() {
        coordinates = (new Coord());
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
