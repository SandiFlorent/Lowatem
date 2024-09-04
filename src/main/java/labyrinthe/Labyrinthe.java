package labyrinthe;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import personnages.IPersonnage;

/**
 *
 * @author professor team
 */
public class Labyrinthe extends ArrayList<ISalle> implements ILabyrinthe {
    
    private IEtage etageCourant = new Etage();
    
    public Labyrinthe(){
        Etage etage1 = new Etage(1);
        Etage etage2 = new Etage(2);
        try {
            etage1.charger("etages/etage1N.txt");
            etage2.charger("etages/etage2N.txt");
            this.addAll(etage1);
            this.addAll(etage2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<ISalle> sallesAccessibles(IPersonnage heros) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public ISalle getEntree() {
        return null;
    }

    @Override
    public ISalle getSortie() {
        return null;   
    }

    @Override
    public IEtage getEtageCourant() {
        return this.etageCourant;
    }

    @Override
    public void setEtageCourant(IEtage etage) {
        this.etageCourant = etage;
    }

    @Override
    public Collection<ISalle> chemin(ISalle u, ISalle v) {
        return null;
    }
    
}
