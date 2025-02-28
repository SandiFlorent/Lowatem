package labyrinthe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import static labyrinthe.ESalle.ENTREE;
import static labyrinthe.ESalle.SORTIE;
import personnages.IPersonnage;

/**
 *
 * @author professor team
 */
public class Labyrinthe extends ArrayList<ISalle> implements ILabyrinthe {

    private IEtage etageCourant;
    private ISalle entree;
    private ISalle sortie;
    public IPersonnage hero;

    public Labyrinthe() {
        //First we charge the floors
        Etage etage1 = new Etage(1);
        Etage etage2 = new Etage(2);
        
        try {
            etage1.charger("etages/etage1N.txt");
            etage2.charger("etages/etage2N.txt");
            this.addAll(etage1);
            this.addAll(etage2);

            etageCourant = etage1;

            //Then we define the entrance and exit
            findEntranceAndExit();

        } catch (IOException e) {
            e.printStackTrace();
            this.clear();
            this.addAll(etage1);
            this.addAll(etage2);
        }
    }

    /**
     * This method will determine the entrance and the exit of the labyrinthe
     */
    private void findEntranceAndExit() {
        int i = 0;
        ISalle salle;
        // While We don't have both, we don't stop for we can't play without them.
        while (entree == null || sortie == null && i < this.size()) {
            salle = this.get(i);
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
        Collection<ISalle> salleAccessible = new ArrayList<>();
        ISalle salleCourante = heros.getPosition();
        for (ISalle s : this) {
            if (salleCourante.estAdjacente(s)) {
                salleAccessible.add(s);
            }
        }
        return salleAccessible;
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
