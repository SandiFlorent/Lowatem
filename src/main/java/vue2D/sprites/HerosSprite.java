/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue2D.sprites;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import labyrinthe.ILabyrinthe;
import labyrinthe.ISalle;
import labyrinthe.Labyrinthe;
import personnages.Heros;
import personnages.IPersonnage;

/**
 *
 * @author sandi
 */
public class HerosSprite extends ASprite implements EventHandler<KeyEvent> {

    Labyrinthe labyrinthe;
    public HerosSprite(IPersonnage personnage, ILabyrinthe labyrinthe) {
        super(personnage, new Image ("file:icons/link/LinkRunShieldL1.gif") );
        personnage.setPosition(labyrinthe.getEntree());
        this.labyrinthe = (Labyrinthe)labyrinthe;
    }

    @Override
    public void handle(KeyEvent t) {
        
        switch (t.getCode()) {
            case M:
                ((Heros) personnage).salleChoisie = getNewPositionStairs(1);
                break;
            case D:
                ((Heros) personnage).salleChoisie = getNewPositionStairs(-1);
                break;
            case UP:
                ((Heros) personnage).salleChoisie = getNewPosition(0, -1);
                break;
            case DOWN:
                ((Heros) personnage).salleChoisie = getNewPosition(0, 1);
                break;
            case LEFT:
                ((Heros) personnage).salleChoisie = getNewPosition(-1, 0);
                break;
            case RIGHT:
                ((Heros) personnage).salleChoisie = getNewPosition(1, 0);
                break;
        }
        
    }

    /**
     * This method finds the a room that is whether one level above or one level
     * bellow
     * It's also checking if the room is correct
     *
     * @param number the level difference, -1 so specify it's one level bellow,
     * 1 is one level above
     * @return the chosen room
     */
    private ISalle getNewPositionStairs(int number) {
        ISalle salleChoisie = personnage.getPosition();
        for (ISalle salle : labyrinthe.sallesAccessibles(personnage)) {
            if (salle.getEtage().getNum() == personnage.getPosition().getEtage().getNum() + number) {
                salleChoisie = salle;
            }
        }
        return salleChoisie;
    }

    /**
     * This method finds the a room that is wheter up, right, left or right of
     * the actual position
     * It's also checking if the room is correct
     *
     * @param numberX to specify the X gap, -1 left and 1 right
     * @param numberY to specify the Y gap, -1 down and 1 up
     * @return the chosen room
     */
    private ISalle getNewPosition(int numberX, int numberY) {
        ISalle salleChoisie = personnage.getPosition();
        System.out.println("Les salles accessibles sont :"+labyrinthe.sallesAccessibles(personnage));
        System.out.println("");
        System.out.println("");
        for (ISalle salle : labyrinthe.sallesAccessibles(personnage)) {
            if (salle.getX() == personnage.getPosition().getX() + numberX
                    && salle.getY() == personnage.getPosition().getY() + numberY) {
                salleChoisie = salle;
            }
        }
        System.out.println("La salle choisie est " + salleChoisie);
        System.out.println("La salle actuelle est " + personnage.getPosition());
        if (salleChoisie == null){
            return personnage.getPosition();
        }
        ((Heros)personnage).setPosition(salleChoisie);
        return salleChoisie;
    }

}
