package labyrinthe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author INFO Professors team
 */
public class Etage extends ArrayList<ISalle> implements IEtage {

    private int largeur;
    private int hauteur;
    private int num;
    
    public Etage(){
        largeur = 40;
        hauteur = 40;
        num = 1;
    }
    
    public Etage(int id){
        this.num = id;
    }
 
    @Override
    public void charger(String file) throws IOException {
        this.clear();
        List<String> lignes = Files.readAllLines(Paths.get(file));
        // dimensions
        String entete = lignes.get(0);
        String[] mots = entete.split(" ");
        largeur = Integer.parseInt(mots[0]);
        hauteur = Integer.parseInt(mots[1]);
        lignes.remove(0);
        // salles
        for (String ligne : lignes){
            mots = ligne.split(" ");
            int x = Integer.parseInt(mots[0]);
            int y = Integer.parseInt(mots[1]);
            ESalle type;
            switch (mots[2]){
                case "N":
                    type = ESalle.NORMALE;
                    break;
                case "M":
                    type = ESalle.ESCALIER_MONTANT;
                    break;
                case "D":
                    type = ESalle.ESCALIER_DESCENDANT;
                    break;
                case "E":
                    type = ESalle.ENTREE;
                    break;
                case "S":
                    type = ESalle.SORTIE;
                    break;
                //In case there's an issue we set the type to null.    
                default :
                    type = null;
                    break;
            }
            this.add(new Salle(x, y, type, this));
        }
    }
    
    @Override
    public int getLargeur() {
        return largeur;
    }

    @Override
    public int getHauteur() {
        return hauteur;
    }  

    @Override
    public int getNum() {
        return num;
    }
    

}
