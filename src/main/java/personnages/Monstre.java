/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personnages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import labyrinthe.ILabyrinthe;
import labyrinthe.ISalle;
import labyrinthe.Labyrinthe;

/**
 *
 * @author sandi
 */
public class Monstre extends APersonnage {

    protected Random Random;
    protected ILabyrinthe labyrinthe;

    public Monstre(Labyrinthe labyrinthe) {
        this.labyrinthe = labyrinthe;
        Random = new Random();
        //They'll get a random starting point that is not the player's one
        randomInitialPosition();
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
        return randomRoom(sallesAccessibles);
    }

    /**
     * This method will choose a random room from the acessible ones
     *
     * @param sallesAccessibles the accessible rooms
     * @return the randomly chosen room, or the actual position if there's no
     * accessible one
     */
    protected ISalle randomRoom(Collection<ISalle> sallesAccessibles) {
        // If there's no room, there's no choice
        if (sallesAccessibles.isEmpty()) {
            return this.positionCourante;
        }

        // Creating an ArrayList from the sallesAccessibles grants the ability to choose a random room
        List<ISalle> list = new ArrayList<>(sallesAccessibles);
        return list.get(Random.nextInt(sallesAccessibles.size()));
    }

    protected void randomInitialPosition() {
        do {
            this.positionCourante = randomRoom(labyrinthe);
        } while (this.positionCourante == labyrinthe.getEntree());
    }
}
