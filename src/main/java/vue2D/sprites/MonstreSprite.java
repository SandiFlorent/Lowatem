/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue2D.sprites;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.image.Image;
import labyrinthe.ESalle;
import labyrinthe.ILabyrinthe;
import labyrinthe.ISalle;
import labyrinthe.Labyrinthe;
import personnages.IPersonnage;

/**
 *
 * @author sandi
 */
public class MonstreSprite extends ASprite{
    
    Labyrinthe labyrinthe;
    
    public MonstreSprite(IPersonnage personnage, ILabyrinthe labyrinthe) {
        super(personnage, new Image("file:icons/link/monstre0.gif"));
    }
}

