/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labyrinthe;

import java.util.Objects;

/**
 *
 * @author mflorent
 */
public class Salle implements ISalle {

    /**
     * The x coord
     */
    public int X;

    /**
     * The y coord
     */
    public int Y;

    /**
     * The room's type
     */
    public ESalle Type;

    /**
     * The room's level
     */
    public Etage Etage;

    public Salle(int x, int y, ESalle type, Etage etage) {
        X = x;
        Y = y;
        Type = type;
        Etage = etage;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.X;
        hash = 59 * hash + this.Y;
        hash += this.Etage.getNum();
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
        final Salle other = (Salle) obj;
        if (this.X != other.X) {
            return false;
        }
        if (this.Y != other.Y) {
            return false;
        }
        return Objects.equals(this.Etage, other.Etage);
    }

    @Override
    public int getX() {
        return X;
    }

    @Override
    public int getY() {
        return Y;
    }

    @Override
    public ESalle getType() {
        return Type;
    }

    @Override
    public IEtage getEtage() {
        return Etage;
    }

    /**
     * Determines whether or not a room is adjacent to another one
     *
     * @param autre the other room to be compared with this
     * @return true if they are adjacent, false otherwise
     */
    @Override
    public boolean estAdjacente(ISalle autre) {
        switch (this.getType()) {
            case ESCALIER_MONTANT:
            case ESCALIER_DESCENDANT:
                return checkStairsAdjency(autre);
            default:
                return normalAdjacency(autre);
        }
    }

    /**
     * Computes the distance between two coordinates
     *
     * @param coord1 the first coord
     * @param coord2 the second coord
     * @return the absolute value between both, hence the distance
     */
    private int distanceCoord(int coord1, int coord2) {
        return Math.abs(coord1 - coord2);
    }

    /**
     * Determines if two rooms of type ESACALIER_something are directly next to
     * each other in terms of floor levels. 
     *
     * @param autre the other stair to be compared with
     * @return
     */
    private boolean stairsDistanceCoords(ISalle autre) {
        if (this.getType() == ESalle.ESCALIER_MONTANT) {
            return (autre.getEtage().getNum() == this.getEtage().getNum() + 1);
        }
        return autre.getEtage().getNum() == this.getEtage().getNum() - 1;
    }

    /**
     * This method determines if two rooms of the same level are on the same level
     * @param autre
     * @return 
     */
    private boolean normalAdjacency(ISalle autre) {
        if (autre.getEtage().getNum() != this.getEtage().getNum()) {
            return false;
        }
        if ((distanceCoord(this.getX(), autre.getX()) == 1) && (this.getY() == autre.getY())) {
            return true;
        }
        return (distanceCoord(this.getY(), autre.getY()) == 1) && (this.getX() == autre.getX());
    }

    /**
     * This function will determine if this object and another one are one the
     * same coords
     *
     * @param other the object to be compared with this
     * @return true if both objects are on the same coords, false otherwise
     */
    private boolean isOnSameCoords(ISalle other) {
        return this.getX() == other.getX() && this.getY() == other.getY();
    }

    /**
     * This method checks according to the stair type if there's an adjency
     *
     * @param autre the other stair to be compared with this object
     * @return true if there's an adjency, false otherwise
     */
    private boolean checkStairsAdjency(ISalle autre) {
        ESalle otherStairType;
        otherStairType = ESalle.ESCALIER_MONTANT;

        // We check whether we have an ascendent or a descendent stair
        if (this.getType() == ESalle.ESCALIER_MONTANT) {
            otherStairType = ESalle.ESCALIER_DESCENDANT;
        }

        // If it's not the complementary stair type, it still might be an adjacent room
        if (autre.getType() != otherStairType) {
            return normalAdjacency(autre);
        }

        // False if not the direct superior or inferior floor
        if (!stairsDistanceCoords(autre)) {
            return false;
        }
        //True otherwise
        return isOnSameCoords(autre);
    }

    @Override
    public String toString() {
        return "Salle{" + "X=" + X + ", Y=" + Y + ", Type=" + Type + ", Etage=" + Etage.getNum() + '}';
    }

}
