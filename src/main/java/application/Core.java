package application;

import java.util.Collection;
import java.util.Random;
import javafx.scene.image.Image;
import labyrinthe.ILabyrinthe;
import labyrinthe.ISalle;
import labyrinthe.Labyrinthe;
import personnages.Dragon;
import personnages.Heros;
import personnages.Monstre;
import vue2D.IVue;
import vue2D.sprites.HerosSprite;
import vue2D.sprites.ISprite;
import vue2D.sprites.MonstreSprite;

/**
 *
 * @author arpecher
 */
public class Core {

    ISprite heros;
    ILabyrinthe labyrinthe;

    protected void initLabyrinthe() {
        // creation du labyrinthe
        labyrinthe = new labyrinthe.LabyrintheGraphe();
    }

    protected void initSprites(IVue vue) {
        // creation du heros 
        Heros h = new personnages.Heros(labyrinthe.getEntree());
        this.heros = new HerosSprite(h, labyrinthe);
        vue.add(this.heros);

        // Creating a random number of monsters
        Monstre monstre;
        ISprite monstreSprite;
        Random random = new Random();

        // There'll be between 8 and 12 monsters
        int randomNumberOfMonsters = random.nextInt(5) + 8;
        for (int i = 0; i < randomNumberOfMonsters; i++) {
            monstre = new Monstre((Labyrinthe) labyrinthe);
            monstreSprite = new MonstreSprite(monstre);
            vue.add(monstreSprite);
        }

        // Creating a dragon
        Dragon dragon = new Dragon((Labyrinthe) labyrinthe);
       ISprite dragonSprite = new MonstreSprite(dragon, new Image("file:icons/dragon.gif"));
        vue.add(dragonSprite);
    }

    protected void jeu(IVue vue) {
        // boucle principale
        ISalle destination = null;
        while (!labyrinthe.getSortie().equals(heros.getPosition())) {
            // ajustement etage courant: celui du héros
            labyrinthe.setEtageCourant(heros.getPosition().getEtage());
            // choix et deplacements de chaque sprite
            for (ISprite s : vue) {
                Collection<ISalle> sallesAccessibles = labyrinthe.sallesAccessibles(s);
                destination = s.faitSonChoix(sallesAccessibles); // on demande au personnage de faire son choix de salle
                s.setPosition(destination); // deplacement

            }
            // detection des collisions
            boolean collision = false;
            ISprite monstre = null;
            for (ISprite s : vue) {
                if (s != heros) {
                    if (s.getPosition() == heros.getPosition()) {
                        System.out.println("Collision !!");
                        collision = true;
                        monstre = s;
                    }
                }
            }
            if (collision) {
                vue.remove(monstre);
                vue.remove(heros);
                System.out.println("Perdu !");
                System.out.println("Plus que " + vue.size() + " personnages ...");
            }

            temporisation(50);
        }
        System.out.println("Gagné!");
    }

    protected void temporisation(int nb) {
        try {
            Thread.sleep(nb); // pause de nb millisecondes
        } catch (InterruptedException ie) {
        }
    }
}
