package vue2D.javafx;

import java.util.Collection;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import labyrinthe.ILabyrinthe;
import labyrinthe.ISalle;
import vue2D.AVue;
import vue2D.sprites.ISprite;
import labyrinthe.IEtage;

/**
 *
 * @author INFO Professors team
 */
public class Dessin extends Canvas {

    private Collection<ISprite> sprites;
    private int unite;

    // donnees labyrinthe labyrinthe
    private ILabyrinthe labyrinthe;
    private ISalle entree;
    private ISalle sortie;
    private int largeur;
    private int hauteur;

    private GraphicsContext tampon;
    private Image murImage;
    private Image solImage;
    private Image escalierM;
    private Image escalierD;
    private Image salle;
    private Image entreeImage;
    private int tailleLinkH = 6;
    private int tailleLinkL = 2;

    public Dessin(ILabyrinthe labyrinthe, Collection<ISprite> sprites) {
        this.labyrinthe = labyrinthe;
        this.sprites = sprites;
        this.unite = AVue.UNITE;
        entree = labyrinthe.getEntree();
        sortie = labyrinthe.getSortie();
        largeur = labyrinthe.getEtageCourant().getLargeur();
        hauteur = labyrinthe.getEtageCourant().getHauteur();
        setWidth(largeur * unite);
        setHeight(hauteur * unite);
        tampon = this.getGraphicsContext2D();
        chargementImages();
        dessinFond();
        dessinSalles(this.labyrinthe.getEtageCourant());
    }

    public void chargementImages() {
        murImage = new Image("file:icons/mur0.gif");
        solImage = new Image("file:icons/pyramide.png");
        escalierM = new Image("file:icons/up.gif");
        escalierD = new Image("file:icons/down.gif");
        salle = new Image("file:icons/bluerock.gif");
        entreeImage = new Image("file:icons/sortie.gif");
    }

    public void dessinFond() {
        tampon.drawImage(solImage, 0, 0, unite * largeur,
                unite * hauteur);
    }

    public void dessinSalles(IEtage etage) {
        for (ISalle s : etage) {
            Color c = Color.rgb(200, 200, 200);
            dessinSalle(s, c);
            
        }
    }

    public void dessinSalle(ISalle s, Color c) {
        switch (s.getType()){
            case ESCALIER_MONTANT : 
                tampon.drawImage(escalierM, s.getX() * unite, s.getY() * unite, unite,
                unite);
                break;
            case ESCALIER_DESCENDANT : 
                tampon.drawImage(escalierD, s.getX() * unite, s.getY() * unite, unite,
                unite);
                break;
            case SORTIE :
                tampon.drawImage(entreeImage, s.getX() * unite, s.getY() * unite, unite,
                unite);
                break;
            case ENTREE : 
                Color r = Color.rgb(200, 0, 0);
                tampon.setFill(r);
                tampon.fillRect(s.getX() * unite, s.getY() * unite, unite,
                unite);
                break;
            default : 
                tampon.setFill(c);
                tampon.fillRect(s.getX() * unite, s.getY() * unite, unite,
                unite);
                // The following code draws the room with a certain type of image
                //tampon.drawImage(salle, s.getX() * unite, s.getY() * unite, unite, unite);
            }
    }

    public void dessinPlusCourtChemin(ISprite p) {
        // ...
    }

}
