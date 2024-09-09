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
import static vue2D.AVue.UNITE;

/**
 *
 * @author sandi
 */
public abstract class ASprite implements ISprite {

    public IPersonnage personnage;
    public int X;
    public int Y;
    public Image image;


    public ASprite(IPersonnage personnage, Image image) {
        this.personnage = personnage;
        this.image = image;
    }

    @Override
    public void dessiner(GraphicsContext g) {
        g.drawImage(image, X, Y);
    }

    @Override
    public void setCoordonnees(int xpix, int ypix) {
        X = xpix;
        Y = ypix;
    }
    @Override
    public ISalle faitSonChoix(Collection<ISalle> sallesAccessibles) {
        ISalle dest = personnage.faitSonChoix(sallesAccessibles);
        setCoordonnees((dest.getX()*UNITE), dest.getY()*UNITE);
        return dest;
    }

    @Override
    public ISalle getPosition() {
        return personnage.getPosition();
    }

    @Override
    public void setPosition(ISalle s) {
        this.X = s.getX()*UNITE;
        this.Y = s.getX()*UNITE;
        personnage.setPosition(s);
        setCoordonnees(X, Y);
    }
    

}
