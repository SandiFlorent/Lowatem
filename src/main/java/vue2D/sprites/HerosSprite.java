/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue2D.sprites;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import labyrinthe.Labyrinthe;
import personnages.IPersonnage;

/**
 *
 * @author sandi
 */
public class HerosSprite extends ASprite implements EventHandler<KeyEvent> {
    Labyrinthe labyrinthe;

    public HerosSprite(IPersonnage personnage, Image image, Labyrinthe labyrinthe) {
        super(personnage, image);
        this.setPosition(labyrinthe.getEntree());
    }

    @Override
    public void handle(KeyEvent t) {
        switch (t.getCode()) {
            case M:
                this.faitSonChoix(labyrinthe.sallesAccessibles(personnage));
                break;
            case D:
                break;
            case UP:
                break;
            case DOWN:
                break;
            case LEFT:
                break;
            case RIGHT:
                break;
                
        }
    }

}
