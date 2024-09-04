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
    private ISalle entree;
    private ISalle sortie;

    public Labyrinthe() {
        //First we charge the floors
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

        //Then we define the entrance and exit
        int i = 0;
        while (entree == null && sortie == null && i < this.size()){
            
            ISalle salle = this.get(i);
            switch (salle.getType()) {
                case ENTREE:
                    entree = salle;
                    break;
                case SORTIE:
                    sortie = salle;
                    break;
                default:
                    break;
            }
            i++;
        }
    }

    @Override
    public Collection<ISalle> sallesAccessibles(IPersonnage heros) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ISalle getEntree() {
        return entree;
    }

    @Override
    public ISalle getSortie() {
        return sortie;
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
