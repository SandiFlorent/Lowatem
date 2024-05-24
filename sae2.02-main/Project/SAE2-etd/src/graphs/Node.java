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

    public Coord Coordonnées;

    public Node(Coord uneCoordonnées) {
        Coordonnées = uneCoordonnées;
    }

    public Node() {
        Coordonnées = (new Coord());
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
        return Coordonnées.equals(tmp);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.Coordonnées);
        return hash;
    }
}
