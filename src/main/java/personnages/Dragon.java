/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personnages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import labyrinthe.ISalle;
import labyrinthe.Labyrinthe;

/**
 *
 * @author sandi
 */
public class Dragon extends Monstre {

    private ArrayList<ISalle> list;
    private final Labyrinthe LABY;

    public Dragon(Labyrinthe labyrinthe) {
        super(labyrinthe);
        LABY = (Labyrinthe) labyrinthe;
        list = (ArrayList<ISalle>) super.labyrinthe.chemin(this.getPosition(), LABY.hero.getPosition());
    }

    @Override
    public ISalle faitSonChoix(Collection<ISalle> sallesAccessibles) {
        // If the last element of the path is not the hero position, then he has moved
        if (!(list.get(list.size() - 1) == LABY.hero.getPosition())) {
            //Creating the shortest path for the dragon
            list = (ArrayList<ISalle>) super.labyrinthe.chemin(this.getPosition(), LABY.hero.getPosition());
        }
        ISalle s = list.get(1);
        list.remove(1);
        
        return s;
    }
}
