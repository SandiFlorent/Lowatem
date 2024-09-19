/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue2D.sprites;

import java.util.Collection;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import labyrinthe.ISalle;
import personnages.IPersonnage;

/**
 *
 * @author sandi
 */
public abstract class ASprite implements ISprite {

    public IPersonnage personnage;
    public int X;
    private int Y;
    private Image image;
    
    /**
     * While the animation isn't done, the character isn't physically moving
     */
    boolean characterCoordinateMovement;

    public ASprite(IPersonnage personnage) {
        this.personnage = personnage;
    }

    @Override
    public void dessiner(GraphicsContext g) {
        g.drawImage(image, X, Y);
    }

    @Override
    public void setCoordonnees(int xpix, int ypix) {
        if (X == 0 && Y == 0) {
            X = xpix;
            Y = ypix;
        }
        if (X < xpix) {
            X += 2;
            characterCoordinateMovement = false;
        }
        if (Y < ypix) {
            Y += 2;
            characterCoordinateMovement = false;
        }
        if (Y > ypix) {
            Y -= 2;
            characterCoordinateMovement = false;
        }
        if (X > xpix) {
            X -= 2;
            characterCoordinateMovement = false;
        }
        if (X == xpix && Y == ypix) {
            characterCoordinateMovement = true;
        }
    }

    public void initCoordonnees(int xpix, int ypix) {
        X = xpix;
        Y = ypix;
    }

    @Override
    public ISalle faitSonChoix(Collection<ISalle> sallesAccessibles) {
        if (characterCoordinateMovement) {
            return personnage.faitSonChoix(sallesAccessibles);
        }
        return personnage.getPosition();
    }

    @Override
    public ISalle getPosition() {
        return personnage.getPosition();
    }

    @Override
    public void setPosition(ISalle s) {
        personnage.setPosition(s);
    }

    /**
     * This method will set the actual image of the sprite
     *
     * @param image the image to be used
     */
    public void setImage(Image image) {
        this.image = image;
    }

}
