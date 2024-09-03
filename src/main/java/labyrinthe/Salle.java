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
public class Salle implements ISalle{
    
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
    
    public Salle(int x, int y, ESalle type, Etage etage){
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
        hash = 59 * hash + Objects.hashCode(this.Etage);
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

    @Override
    public boolean estAdjacente(ISalle autre) {
        return false;
    }
    
}
