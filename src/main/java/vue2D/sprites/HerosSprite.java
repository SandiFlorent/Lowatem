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
    private final Image IMAGE_L = new Image("file:icons/link/LinkRunShieldL1.gif");
    private final Image IMAGE_R = new Image("file:icons/link/LinkRunR1.gif"); 
    private final Image IMAGE_U = new Image("file:icons/link/LinkRunU1.gif");
    private final Image IMAGE_D = new Image("file:icons/link/LinkRunShieldD1.gif");

    public HerosSprite(IPersonnage personnage, ILabyrinthe labyrinthe) {
        super(personnage);
        setImage(IMAGE_D);
        personnage.setPosition(labyrinthe.getEntree());
        this.labyrinthe = (Labyrinthe) labyrinthe;
    }

    /**
     * Determines, according to the input, the desired direction to move the
     * player to
     *
     * @param t the keyboard input
     */
    @Override
    public void handle(KeyEvent t) {
        //GetNewPosition will find the new room, 
        //all we need to do is to provide the direct higher of lesser value of the coords

        int x = personnage.getPosition().getX();
        int y = personnage.getPosition().getY();
        int floorNum = personnage.getPosition().getEtage().getNum();

        switch (t.getCode()) {
            case M:
                floorNum++;
                setImage(IMAGE_D);
                break;
            case D:
                floorNum--;
                setImage(IMAGE_D);
                break;
            case UP:
                y--;
                setImage(IMAGE_U);
                break;
            case DOWN:
                y++;
                setImage(IMAGE_D);
                break;
            case LEFT:
                x--;
                setImage(IMAGE_L);
                break;
            case RIGHT:
                x++;
                setImage(IMAGE_R);
                break;
        }
        characterCoordinateMovement = false;
        ((Heros) personnage).salleChoisie = getNewPosition(x, y, floorNum);

    }

    /**
     * This method finds the a room that is whether up, right, left or right of
     * the actual position. Also if it's the next or precedent stair. It's also
     * checking if the room is correct
     *
     * @param X to specify the X gap, -1 of the actual position for left and +1
     * for right
     * @param Y to specify the Y gap, -1 of the actual position for down and +1
     * for up
     * @param floorNum specifies on which floor we search the wanted room
     * @return the chosen room
     */
    private ISalle getNewPosition(int X, int Y, int floorNum) {
        ISalle salleChoisie = personnage.getPosition();
        for (ISalle salle : labyrinthe.sallesAccessibles(personnage)) {
            if ((salle.getX() == X) && (salle.getY() == Y) && (salle.getEtage().getNum() == floorNum)) {
                salleChoisie = salle;
            }
        }
        return salleChoisie;
    }
}
