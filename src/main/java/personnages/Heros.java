/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personnages;

import java.util.Collection;
import labyrinthe.ISalle;

/**
 *
 * @author sandi
 */
public class Heros extends APersonnage {

    public ISalle salleChoisie;

    public Heros(ISalle salleChoisie) {
        this.salleChoisie = salleChoisie;
    }

    /**
     * Get the chosen room by the player
     *
     * @param sallesAccessibles the accessible rooms for the player
     * @return the chosen room by the player if it's contained in
     * sallesAccessibles, the current position otherwise
     */
    @Override
    public ISalle faitSonChoix(Collection<ISalle> sallesAccessibles) {
        if (sallesAccessibles.contains(salleChoisie)) {
            return salleChoisie;
        }
        return this.getPosition();
    }
}
