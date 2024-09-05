/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personnages;

import java.util.Collection;
import labyrinthe.ISalle;

/**
 *
 * @author sandi
 */
public abstract class APersonnage implements IPersonnage{

    /**
     * the player's current position
     */
    ISalle positionCourante;
    
    @Override
    public ISalle faitSonChoix(Collection<ISalle> sallesAccessibles) {
        return null;
    }

    @Override
    public ISalle getPosition() {
        return positionCourante;
    }

    @Override
    public void setPosition(ISalle s) {
        positionCourante = s;
    }
    
}
