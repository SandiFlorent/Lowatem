/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personnages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import labyrinthe.ESalle;
import labyrinthe.ISalle;
import labyrinthe.Labyrinthe;

/**
 *
 * @author sandi
 */
public class Monstre extends APersonnage {
    Random Random;
    
    public Monstre(Labyrinthe labyrinthe) {
        Random = new Random();
        //They'll get a random starting point that is not the player's one
        do {
            this.positionCourante = faitSonChoix(labyrinthe);
        } while (this.positionCourante==labyrinthe.getEntree());
        
    }

    /**
     * This method will return a random room that is not an exit, hence the
     * SORTIE type
     *
     * @param sallesAccessibles all the accessible rooms
     * @return the randomly chosen room
     */
    @Override
    public ISalle faitSonChoix(Collection<ISalle> sallesAccessibles) {
        // If there's no room, there's no choice
        if (sallesAccessibles.isEmpty()) {
            return this.positionCourante;
        }
        // Creating an ArrayList from the sallesAccessibles grants the ability to choose a random room
        List<ISalle> list = new ArrayList<>(sallesAccessibles);

        // This predicate will delete every room of type SORTIE.
        list.removeIf(salle -> salle.getType() == ESalle.SORTIE);

        // Now we can safely select a random room in the list
        return (list.get(Random.nextInt(sallesAccessibles.size())));
    }
}

