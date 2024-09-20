package vue2D.javafx;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import labyrinthe.ILabyrinthe;
import labyrinthe.Labyrinthe;
import vue2D.IVue;
import vue2D.AVue;
import vue2D.sprites.ISprite;

/**
 *
 * @author INFO Professors team
 */
public class Vue extends AVue implements IVue {

    Dessin dessin;
    ILabyrinthe labyrinthe;
    public Scene scene;

    public Vue(ILabyrinthe labyrinthe) {
        this.labyrinthe = labyrinthe;
        dessin = new Dessin(labyrinthe, this);
        Group root = new Group();
        this.scene = new Scene(root);
        root.getChildren().add(dessin);
    }

    @Override
    public void dessiner() {
        // recopie du fond (image); murs + salles
        dessin.dessinFond();
        dessin.dessinMur();
        // dessin des salles
        dessin.dessinSalles(this.labyrinthe.getEtageCourant());
        dessin.drawingShortestPath();
        dessin.dessinPlusCourtChemin(dessin.Hero);
        dessin.dessinSprites();
        
        //Stopping the dynamic lighting when the player dies
        if (this.contains(dessin.Hero)){
            dessin.drawingLighting(this.labyrinthe.getEtageCourant());
        }
        
        
    }

    @Override
    public boolean add(ISprite sprite) {
        super.add(sprite);
        // si le sprite est controle par le clavier  
        if (sprite instanceof EventHandler) {
            System.out.println(" registering keylistener ");
            // association de l’ecouteur sur le clavier avec le composant graphique principal
            this.scene.setOnKeyPressed((EventHandler) sprite);

            // Here, we define the hero in sprite in order to make the light
            dessin.Hero = sprite;
            
            //And here we define the hero in order for the dragon to follow him
            Labyrinthe l = (Labyrinthe)labyrinthe;
            l.hero = sprite;
        }
        return true;
    }

}
