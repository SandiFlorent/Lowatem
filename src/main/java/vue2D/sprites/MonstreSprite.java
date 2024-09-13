/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue2D.sprites;

import javafx.scene.image.Image;
import labyrinthe.ILabyrinthe;
import personnages.IPersonnage;

/**
 *
 * @author sandi
 */
public class MonstreSprite extends ASprite {

    public MonstreSprite(IPersonnage personnage) {
        super(personnage, new Image("file:icons/monstre1.gif"));
    }
}
