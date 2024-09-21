package vue2D.javafx;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import labyrinthe.ESalle;
import labyrinthe.Etage;
import labyrinthe.ILabyrinthe;
import labyrinthe.ISalle;
import vue2D.AVue;
import vue2D.sprites.ISprite;
import labyrinthe.IEtage;
import labyrinthe.LabyrintheGraphe;
import labyrinthe.Salle;

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
    private Image dragon;
    private int tailleLinkH = 6;
    private int tailleLinkL = 2;
    public ISprite Hero;
    private int HeroX;
    private int HeroY;
    private Collection<ISalle> ShortestPath;
    // These are used for computing the euclidian distance

    private double Opacity;
    public HashSet<ISalle> VisitedRooms;

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
        HeroX = -2;
        HeroY = -2;
        VisitedRooms = new HashSet<>();
        VisitedRooms.add(this.entree);
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

    /**
     * Draws all the rooms of the labyrinthe's current floor
     *
     * @param etage the current floor
     */
    public void dessinSalles(IEtage etage) {
        for (ISalle s : etage) {
            Color c = Color.rgb(200, 200, 200);
            dessinSalle(s, c);
        }
    }

    /**
     * This method will draw the lighting of every room in a floor It draws
     * black rectangles with opacity changing according to the distance with the
     * player
     *
     * @param etage the current floor
     */
    public void drawingLighting(IEtage etage) {
        for (ISalle currentRoom : etage) {
            if (!VisitedRooms.contains(currentRoom)) {
                drawingLightingForOneRoom(currentRoom);
            }
        }
    }

    private void drawingRooms(Color C, Collection<ISalle> list) {
        tampon.setFill(C);
        for (ISalle s : list) {
            if (isRoomOnCurrentFloor(s)) {
                tampon.fillRect(s.getX() * unite, s.getY() * unite, unite, unite);
            }
        }
    }

    /**
     * This method draws the shortest path between the hero and the exit
     */
    public void drawingShortestPath() {
        LabyrintheGraphe l = (LabyrintheGraphe) this.labyrinthe;
        drawingRooms(new Color(0, 0, 0.5, 0.4), l.Path);
    }

    /**
     * This method checks if a room is on the current floor
     *
     * @param s the room to be checked
     * @return true if the room is on the current floor, false otherwise
     */
    private boolean isRoomOnCurrentFloor(ISalle s) {
        return s.getEtage() == labyrinthe.getEtageCourant();
    }

    /**
     * This method will draw the lighting of one room (also known as fog) It'll
     * basically draw "darkness" on the furthest rooms or monster from the hero
     */
    private void drawingLightingForOneRoom(ISalle currentRoom) {

        /**
         * The following code is the original way to draw the lighting //
         * Calculate the distance between the hero and the current room using
         * Euclidean distance DeltaX = this.Hero.getPosition().getX() -
         * currentRoom.getX(); DeltaY = this.Hero.getPosition().getY() -
         * currentRoom.getY(); Distance = Math.sqrt(DeltaX * DeltaX + DeltaY *
         * DeltaY);
         *
         * // Make the opacity between 0 and 1 for it will crash otherwise
         * Opacity = Math.max(0, Math.min(1, (Distance * 0.14)));
         *
         * // Set the color with opacity for drawing darkness Color color = new
         * Color(0, 0, 0, Opacity); tampon.setFill(color);
         * tampon.fillRect(currentRoom.getX() * unite, currentRoom.getY() *
         * unite, unite, unite);
         *
         */
        // Calculate the distance between the hero and the current room using some graphs methods
        LabyrintheGraphe laby = (LabyrintheGraphe) this.labyrinthe;
        int distance = laby.distanceGraphe(this.Hero.getPosition(), currentRoom);

        // Make the opacity between 0 and 1 for it will crash otherwise
        Opacity = Math.max(0, Math.min(1, (distance * 0.1)));

        // Set the color with opacity for drawing darkness
        Color color = new Color(0, 0, 0, Opacity);
        tampon.setFill(color);
        tampon.fillRect(currentRoom.getX() * unite, currentRoom.getY() * unite, unite, unite);
    }

    public void dessinMur() {
        for (ISalle s : VisitedRooms) {
            if (s.getEtage() == labyrinthe.getEtageCourant()){
                drawSomething(s.getX() + 1, s.getY(), murImage);
                drawSomething(s.getX() - 1, s.getY(), murImage);
                drawSomething(s.getX(), s.getY() + 1, murImage);
                drawSomething(s.getX(), s.getY() - 1, murImage);
            }
            
        }
    }

    private void drawSomething(int xCoord, int yCoord, Image image) {
        tampon.drawImage(image, xCoord * unite, yCoord * unite, unite,
                unite);
    }

    /**
     * This method will draw ONE room
     *
     * @param s the room to be drawed
     * @param c the desired color of the rectangle
     */
    public void dessinSalle(ISalle s, Color c) {
        switch (s.getType()) {
            case ESCALIER_MONTANT:
                tampon.drawImage(escalierM, s.getX() * unite, s.getY() * unite, unite,
                        unite);
                break;
            case ESCALIER_DESCENDANT:
                tampon.drawImage(escalierD, s.getX() * unite, s.getY() * unite, unite,
                        unite);
                break;
            case SORTIE:
                tampon.drawImage(entreeImage, s.getX() * unite, s.getY() * unite, unite,
                        unite);
                break;
            case ENTREE:
                Color r = Color.rgb(0, 200, 0);
                tampon.setFill(r);
                tampon.fillRect(s.getX() * unite, s.getY() * unite, unite,
                        unite);
                break;
            default:

                //The following code draws the room with a white rectangle
                //tampon.setFill(c);
                //tampon.fillRect(s.getX() * unite, s.getY() * unite, unite,unite);
                // The following code draws the room with a certain type of image
                tampon.drawImage(salle, s.getX() * unite, s.getY() * unite, unite, unite);
        }
    }

    /**
     * This method will draw every that is on the current floor
     */
    public void dessinSprites() {
        for (ISprite sprite : sprites) {
            if (spriteOnTheCurrentFloor(sprite)) {
                sprite.setCoordonnees(unite * sprite.getPosition().getX(), unite * sprite.getPosition().getY());
                sprite.dessiner(tampon);
            }
        }
    }

    public void dessinPlusCourtChemin(ISprite p) {
        Color color = new Color(1, 1, 0, 0.4);// Yellow
        // I recalculate the shortest path only and only if the hero has moved.
        if (heroHasMoved()) {
            VisitedRooms.add(this.Hero.getPosition());
            ShortestPath = this.labyrinthe.chemin(this.Hero.getPosition(), sortie);
            updateHeroPosition();
        }
        drawingRooms(color, this.ShortestPath);
    }

    /**
     * This method determines wheter or not a sprite is on the current floor
     *
     * @param spriteA the sprite to be verified
     * @return true if it's on the current floor, false otherwise
     */
    private boolean spriteOnTheCurrentFloor(ISprite spriteA) {
        return spriteA.getPosition().getEtage() == labyrinthe.getEtageCourant();
    }

    /**
     * This method determines wheter or not the hero has moved
     *
     * @return true if he has moved, false otherwise
     */
    private boolean heroHasMoved() {
        return !(Hero.getPosition().getX() == HeroX && Hero.getPosition().getY() == HeroY);
    }

    /**
     * Updtate the coordinates that are used to see if the hero has moved
     */
    private void updateHeroPosition() {
        HeroX = Hero.getPosition().getX();
        HeroY = Hero.getPosition().getY();
    }

}
