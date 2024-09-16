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
            //add a new Salle with the right parameters
            if (!this.add(new Salle(x, y, determineType(mots[2]), this))){
                throw new ExceptionInvalidFile("Invalid file have been charged");
            }
        }
    }
    
    /**
     * This method will determine a type according to the last letter written in the file
     */
    private ESalle determineType(String type){
        switch (type){
                case "N":
                    return ESalle.NORMALE;
                case "M":
                    return ESalle.ESCALIER_MONTANT;
                case "D":
                    return ESalle.ESCALIER_DESCENDANT;
                case "E":
                    return ESalle.ENTREE;
                case "S":
                    return ESalle.SORTIE;
                //In case there's an issue we set the type to null.    
                default :
                    return null;
            }
    }
    
    @Override
    public int getLargeur() {
        return largeur;
    }

    @Override
    public boolean add(ISalle Salle) {
        if(!validCoordinates(Salle)){
            return false;
        }
        // the contains method is using the equals method precedently defined
        if (this.contains(Salle)){
            return false;
        }
        
        super.add(Salle);
        return true;
    } 
    /**
     * This function will determine if the room's coordinates are corrects
     * @param salle the room to verify
     * @return if both x and y coordinates are corrects
     */
    private boolean validCoordinates(ISalle salle){
        return !(salle.getY() > this.getHauteur() || salle.getX() > this.getLargeur() ||
                salle.getY() < 0 || salle.getX() < 0);
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
